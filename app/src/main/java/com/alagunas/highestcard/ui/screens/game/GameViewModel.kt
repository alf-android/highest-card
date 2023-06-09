package com.alagunas.highestcard.ui.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alagunas.data.repositories.GameRepository
import com.alagunas.domain.model.CardSuit
import com.alagunas.domain.model.Player
import com.alagunas.highestcard.ui.items.CardUI
import com.alagunas.highestcard.ui.items.getThumb
import com.alagunas.highestcard.ui.items.toCardUI
import com.alagunas.usecases.game.*
import com.alagunas.usecases.game.getroundwinner.BodyGetRoundWinner
import com.alagunas.usecases.game.getroundwinner.GetRoundWinnerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val startGameUseCase: StartGameUseCase,
    private val winRoundUseCase: WinRoundUseCase,
    private val dealTopUseCase: DealTopUseCase,
    private val getSuitOrderUseCase: GetSuitOrderUseCase,
    private val getRoundWinnerUseCase: GetRoundWinnerUseCase,
    private val getGameWinnerUseCase: GameWinnerUseCase,
    private val gameRepository: GameRepository
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

    private val _showDiscardPilePlayerA: MutableStateFlow<Int> = MutableStateFlow(0)
    val showDiscardPilePlayerA = _showDiscardPilePlayerA.asStateFlow()
    private val _showDiscardPilePlayerB: MutableStateFlow<Int> = MutableStateFlow(0)
    val showDiscardPilePlayerB = _showDiscardPilePlayerB.asStateFlow()

    private val _showSuitsOrder: MutableStateFlow<List<Int>> = MutableStateFlow(listOf())
    val showSuitsOrder = _showSuitsOrder.asStateFlow()

    private val _showWinRoundPlayerA: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val showWinRoundPlayerA = _showWinRoundPlayerA.asStateFlow()
    private val _showWinRoundPlayerB: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val showWinRoundPlayerB = _showWinRoundPlayerB.asStateFlow()

    private val _showNextRound: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val showNextRound = _showNextRound.asStateFlow()

    private val _showWinner: MutableStateFlow<Int?> = MutableStateFlow(null)
    val showWinner = _showWinner.asStateFlow()

    private var playerA = Player()
    private var playerB = Player()
    private var suitsOrder = listOf<CardSuit>()

    init {
        startGame()
    }

    private fun startGame() {
        val players = startGameUseCase(Unit)
        playerA = players[0]
        playerB = players[1]
        _showPilePlayerA.value = playerA.pile.size
        _showPilePlayerB.value = playerB.pile.size
        _showSuitsOrder.value = getSuitsOrderResource()
    }

    fun resetGame() {
        _showCardPlayerA.value = null
        _showCardPlayerB.value = null
        _showPilePlayerA.value = null
        _showPilePlayerB.value = null
        _showDiscardPilePlayerA.value = 0
        _showDiscardPilePlayerB.value = 0
        _showSuitsOrder.value = listOf()
        _showWinner.value = null
        _showNextRound.value = true

        startGame()
    }

    private fun getSuitsOrderResource(): List<Int> {
        val suitsDrawable = mutableListOf<Int>()
        suitsOrder = getSuitOrderUseCase(Unit)
        suitsOrder.forEach {
            suitsDrawable.add(it.getThumb())
        }
        return suitsDrawable
    }

    fun nextRound() {
        if (playerA.pile.isNotEmpty() && playerB.pile.isNotEmpty()) {
            val playerAndDealedCardA = dealTopUseCase(playerA)
            val playerAndDealedCardB = dealTopUseCase(playerB)
            playerA = playerAndDealedCardA.first
            playerB = playerAndDealedCardB.first
            val dealedCardA = playerAndDealedCardA.second
            val dealedCardB = playerAndDealedCardB.second

            _showCardPlayerA.value = dealedCardA?.toCardUI()
            _showCardPlayerB.value = dealedCardB?.toCardUI()
            _showPilePlayerA.value = playerA.pile.size
            _showPilePlayerB.value = playerB.pile.size

            _showWinRoundPlayerA.value = null
            _showWinRoundPlayerB.value = null
            if (dealedCardA != null && dealedCardB != null) {
                val winnerCard = getRoundWinnerUseCase(BodyGetRoundWinner(suitsOrder, Pair(dealedCardA, dealedCardB)))
                winnerCard?.let {
                    if (it.faceName.value == dealedCardA.faceName.value && it.suit == dealedCardA.suit) {
                        playerA = winRoundUseCase(playerA)
                        _showDiscardPilePlayerA.value = playerA.discardPile
                        _showWinRoundPlayerA.value = true
                        _showWinRoundPlayerB.value = false
                    } else if (it.faceName.value == dealedCardB.faceName.value && it.suit == dealedCardB.suit) {
                        playerB = winRoundUseCase(playerB)
                        _showDiscardPilePlayerB.value = playerB.discardPile
                        _showWinRoundPlayerA.value = false
                        _showWinRoundPlayerB.value = true
                    }
                }
            }

            //Check if the game is finished
            if (showPilePlayerA.value == 0 && showPilePlayerB.value == 0) {
                getWinner()
                saveGame()
                _showNextRound.value = false
            }
        }
    }

    /**
     * The winner could be:
     * Player A (value = 1)
     * Player B (value = 2)
     * or draw (value = 0)
     */
    private fun getWinner() {
        val winnerPlayer = getGameWinnerUseCase(Pair(playerA, playerB))
        _showWinner.value = when (winnerPlayer?.id) {
            playerA.id -> 1
            playerB.id -> 2
            else -> 0
        }
    }

    private fun saveGame() {
        viewModelScope.launch {
            gameRepository.setGame(Pair(playerA, playerB))
        }
    }
}