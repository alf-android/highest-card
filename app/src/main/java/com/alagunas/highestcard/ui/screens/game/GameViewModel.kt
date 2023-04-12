package com.alagunas.highestcard.ui.screens.game

import androidx.lifecycle.ViewModel
import com.alagunas.domain.model.Card
import com.alagunas.domain.model.Player
import com.alagunas.highestcard.ui.items.CardUI
import com.alagunas.highestcard.ui.items.toCardUI
import com.alagunas.usecases.game.DealTopUseCase
import com.alagunas.usecases.game.StartGameUseCase
import com.alagunas.usecases.game.WinRoundUseCase
import kotlinx.coroutines.flow.*

class GameViewModel(
    private val startGameUseCase: StartGameUseCase,
    private val winRoundUseCase: WinRoundUseCase,
    private val dealTopUseCase: DealTopUseCase
) : ViewModel() {

    private val TAG = GameViewModel::class.java.simpleName

    private val _showPilePlayerA: MutableStateFlow<Int?> = MutableStateFlow(null)
    val showPilePlayerA = _showPilePlayerA.asStateFlow()
    private val _showPilePlayerB: MutableStateFlow<Int?> = MutableStateFlow(null)
    val showPilePlayerB = _showPilePlayerB.asStateFlow()


    private val _showCardPlayerA: MutableStateFlow<CardUI?> = MutableStateFlow(null)
    val showCardPlayerA = _showCardPlayerA.asStateFlow()
    private val _showCardPlayerB: MutableStateFlow<CardUI?> = MutableStateFlow(null)
    val showCardPlayerB = _showCardPlayerB.asStateFlow()

    private val _showWinsPlayerA: MutableStateFlow<Int> = MutableStateFlow(0)
    val showWinsPlayerA = _showWinsPlayerA.asStateFlow()
    private val _showWinsPlayerB: MutableStateFlow<Int> = MutableStateFlow(0)
    val showWinsPlayerB = _showWinsPlayerB.asStateFlow()


    private var playerA: Player? = null
    private var playerB: Player? = null

    init {
        startGame()
    }

    private fun startGame() {
        val players = startGameUseCase(Unit)
        playerA = players[0]
        playerB = players[1]
    }

    fun resetGame() {
        _showCardPlayerA.value = null
        _showCardPlayerB.value = null
        _showPilePlayerA.value = null
        _showPilePlayerB.value = null
        _showWinsPlayerA.value = 0
        _showWinsPlayerB.value = 0

        startGame()
    }

    fun nextRound() {
        if (playerA != null && playerB != null) {
            if (playerA!!.getPileSize() > 0 && playerB!!.getPileSize() > 0) {
                val dealedCardA = dealTopUseCase(playerA!!)
                val dealedCardB = dealTopUseCase(playerB!!)

                _showCardPlayerA.value = dealedCardA.toCardUI()
                _showCardPlayerB.value = dealedCardB.toCardUI()
                _showPilePlayerA.value = playerA!!.getPileSize()
                _showPilePlayerB.value = playerB!!.getPileSize()

                if (isPlayerAWinner(dealedCardA, dealedCardB)) {
                    winRoundUseCase(playerA!!, listOf(dealedCardA, dealedCardB))
                    _showWinsPlayerA.value = showWinsPlayerA.value + 1
                } else {
                    winRoundUseCase(playerB!!, listOf(dealedCardA, dealedCardB))
                    _showWinsPlayerB.value = showWinsPlayerB.value + 1
                }
            }
        }
    }

    //TODO: check value of suits in case of draw
    private fun isPlayerAWinner(cardPlayerA: Card, cardPlayerB: Card) =
        cardPlayerA.faceName.value > cardPlayerB.faceName.value
}