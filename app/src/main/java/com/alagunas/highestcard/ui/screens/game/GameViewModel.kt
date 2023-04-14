package com.alagunas.highestcard.ui.screens.game

import androidx.lifecycle.ViewModel
import com.alagunas.domain.model.CardSuit
import com.alagunas.domain.model.Player
import com.alagunas.highestcard.ui.items.CardUI
import com.alagunas.highestcard.ui.items.getThumb
import com.alagunas.highestcard.ui.items.toCardUI
import com.alagunas.usecases.game.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val startGameUseCase: StartGameUseCase,
    private val winRoundUseCase: WinRoundUseCase,
    private val dealTopUseCase: DealTopUseCase,
    private val getSuitOrderUseCase: GetSuitOrderUseCase,
    private val getRoundWinnerUseCase: GetRoundWinnerUseCase,
    private val getGameWinnerUseCase: GameWinnerUseCase
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

            _showCardPlayerA.value = dealedCardA.toCardUI()
            _showCardPlayerB.value = dealedCardB.toCardUI()
            _showPilePlayerA.value = playerA.pile.size
            _showPilePlayerB.value = playerB.pile.size

            val winnerCard = getRoundWinnerUseCase(suitsOrder, Pair(dealedCardA, dealedCardB))
            winnerCard?.let {
                if (it.faceName.value == dealedCardA.faceName.value && it.suit == dealedCardA.suit) {
                    playerA = winRoundUseCase(playerA)
                    _showDiscardPilePlayerA.value = playerA.discardPile
                } else if (it.faceName.value == dealedCardB.faceName.value && it.suit == dealedCardB.suit) {
                    playerB = winRoundUseCase(playerB)
                    _showDiscardPilePlayerB.value = playerB.discardPile
                }
            }
        }
    }

    //TODO SHOW WINNER!
    fun getWinner() {
        //Player A, Player B or draw (null)
        val winnerPlayer = getGameWinnerUseCase(Pair(playerA, playerB))

    }
}