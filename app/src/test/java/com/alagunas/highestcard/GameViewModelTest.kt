package com.alagunas.highestcard

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.CardFaceName
import com.alagunas.domain.model.CardSuit
import com.alagunas.domain.model.Player
import com.alagunas.highestcard.ui.items.CardUI
import com.alagunas.highestcard.ui.screens.game.GameViewModel
import com.alagunas.usecases.game.*
import com.alagunas.usecases.game.getroundwinner.BodyGetRoundWinner
import com.alagunas.usecases.game.getroundwinner.GetRoundWinnerUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel
    private val startGameUseCase: StartGameUseCase = mockk(relaxed = true)
    private val winRoundUseCase: WinRoundUseCase = mockk(relaxed = true)
    private val dealTopUseCase: DealTopUseCase = mockk(relaxed = true)
    private val getSuitOrderUseCase: GetSuitOrderUseCase = mockk(relaxed = true)
    private val getRoundWinnerUseCase: GetRoundWinnerUseCase = mockk(relaxed = true)
    private val getGameWinnerUseCase: GameWinnerUseCase = mockk(relaxed = true)

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var playerA: Player
    private lateinit var playerB: Player

    private lateinit var order: List<CardSuit>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        playerA = Player(
            pile = listOf(
                Card(CardFaceName.TWO, CardSuit.DIAMONDS),
                Card(CardFaceName.THREE, CardSuit.DIAMONDS),
                Card(CardFaceName.FOUR, CardSuit.DIAMONDS),
                Card(CardFaceName.FIVE, CardSuit.DIAMONDS),
                Card(CardFaceName.SIX, CardSuit.DIAMONDS),
                Card(CardFaceName.SEVEN, CardSuit.DIAMONDS),
                Card(CardFaceName.EIGHT, CardSuit.DIAMONDS),
                Card(CardFaceName.NINE, CardSuit.DIAMONDS),
                Card(CardFaceName.TEN, CardSuit.DIAMONDS),
                Card(CardFaceName.JACK, CardSuit.DIAMONDS),
                Card(CardFaceName.QUEEN, CardSuit.DIAMONDS),
                Card(CardFaceName.KING, CardSuit.DIAMONDS),
                Card(CardFaceName.ACE, CardSuit.DIAMONDS)
            )
        )

        playerB = Player(
            pile = listOf(
                Card(CardFaceName.TWO, CardSuit.HEARTS),
                Card(CardFaceName.THREE, CardSuit.HEARTS),
                Card(CardFaceName.FOUR, CardSuit.HEARTS),
                Card(CardFaceName.FIVE, CardSuit.HEARTS),
                Card(CardFaceName.SIX, CardSuit.HEARTS),
                Card(CardFaceName.SEVEN, CardSuit.HEARTS),
                Card(CardFaceName.EIGHT, CardSuit.HEARTS),
                Card(CardFaceName.NINE, CardSuit.HEARTS),
                Card(CardFaceName.TEN, CardSuit.HEARTS),
                Card(CardFaceName.JACK, CardSuit.HEARTS),
                Card(CardFaceName.QUEEN, CardSuit.HEARTS),
                Card(CardFaceName.KING, CardSuit.HEARTS),
                Card(CardFaceName.ACE, CardSuit.HEARTS)
            )
        )

        order = listOf(
            CardSuit.CLUBS,
            CardSuit.HEARTS,
            CardSuit.SPADES,
            CardSuit.DIAMONDS
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldStartGameWithSameCardsAndFourSuits() {
        runTest(testDispatcher) {
            coEvery { startGameUseCase(Unit) } returns listOf(playerA, playerB)
            coEvery { getSuitOrderUseCase(Unit) } returns order
            viewModel = GameViewModel(
                startGameUseCase,
                winRoundUseCase,
                dealTopUseCase,
                getSuitOrderUseCase,
                getRoundWinnerUseCase,
                getGameWinnerUseCase
            )

            assertEquals(4, viewModel.showSuitsOrder.value.size)
            assertEquals(13, viewModel.showPilePlayerA.value)
            assertEquals(13, viewModel.showPilePlayerB.value)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldWinRoundWhoHasTheMostValue() {
        runTest(testDispatcher) {
            coEvery { startGameUseCase(Unit) } returns listOf(playerA, playerB)
            coEvery { getSuitOrderUseCase(Unit) } returns order
            coEvery { dealTopUseCase(playerA) } returns Pair(
                playerA.copy(pile = playerA.pile.take(playerA.pile.size - 1)),
                Card(CardFaceName.ACE, CardSuit.DIAMONDS)
            )
            coEvery { dealTopUseCase(playerB) } returns Pair(
                playerB.copy(pile = playerB.pile.take(playerB.pile.size - 1)),
                Card(CardFaceName.ACE, CardSuit.HEARTS)
            )
            coEvery {
                getRoundWinnerUseCase(
                    BodyGetRoundWinner(
                        order,
                        Pair(
                            Card(CardFaceName.ACE, CardSuit.DIAMONDS),
                            Card(CardFaceName.ACE, CardSuit.HEARTS)
                        )
                    )
                )
            } returns Card(CardFaceName.ACE, CardSuit.HEARTS)

            coEvery { winRoundUseCase(any()) } returns Player(wins = 1, discardPile = 2)

            viewModel = GameViewModel(
                startGameUseCase,
                winRoundUseCase,
                dealTopUseCase,
                getSuitOrderUseCase,
                getRoundWinnerUseCase,
                getGameWinnerUseCase
            )

            viewModel.nextRound()
        }

        assertEquals(CardUI("A", R.drawable.diamonds), viewModel.showCardPlayerA.value)
        assertEquals(CardUI("A", R.drawable.hearts), viewModel.showCardPlayerB.value)
        assertEquals(12, viewModel.showPilePlayerA.value)
        assertEquals(12, viewModel.showPilePlayerB.value)
        assertEquals(false, viewModel.showWinRoundPlayerA.value)
        assertEquals(true, viewModel.showWinRoundPlayerB.value)
        assertEquals(0, viewModel.showDiscardPilePlayerA.value)
        assertEquals(2, viewModel.showDiscardPilePlayerB.value)
        assertEquals(true, viewModel.showNextRound.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldResetGameCorrectly() {
        runTest(testDispatcher) {
            coEvery { startGameUseCase(Unit) } returns listOf(playerA, playerB)
            coEvery { getSuitOrderUseCase(Unit) } returns order
            coEvery { dealTopUseCase(playerA) } returns Pair(
                playerA.copy(pile = playerA.pile.take(playerA.pile.size - 1)),
                Card(CardFaceName.ACE, CardSuit.DIAMONDS)
            )
            coEvery { dealTopUseCase(playerB) } returns Pair(
                playerB.copy(pile = playerB.pile.take(playerB.pile.size - 1)),
                Card(CardFaceName.ACE, CardSuit.HEARTS)
            )
            coEvery {
                getRoundWinnerUseCase(
                    BodyGetRoundWinner(
                        order,
                        Pair(
                            Card(CardFaceName.ACE, CardSuit.DIAMONDS),
                            Card(CardFaceName.ACE, CardSuit.HEARTS)
                        )
                    )
                )
            } returns Card(CardFaceName.ACE, CardSuit.HEARTS)

            coEvery { winRoundUseCase(any()) } returns Player(wins = 1, discardPile = 2)

            viewModel = GameViewModel(
                startGameUseCase,
                winRoundUseCase,
                dealTopUseCase,
                getSuitOrderUseCase,
                getRoundWinnerUseCase,
                getGameWinnerUseCase
            )

            viewModel.resetGame()
        }

        assertEquals(null, viewModel.showCardPlayerA.value)
        assertEquals(null, viewModel.showCardPlayerB.value)
        assertEquals(13, viewModel.showPilePlayerA.value)
        assertEquals(13, viewModel.showPilePlayerB.value)
        assertEquals(null, viewModel.showWinRoundPlayerA.value)
        assertEquals(null, viewModel.showWinRoundPlayerB.value)
        assertEquals(0, viewModel.showDiscardPilePlayerA.value)
        assertEquals(0, viewModel.showDiscardPilePlayerB.value)
        assertEquals(true, viewModel.showNextRound.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldShowTheWinnerAndHideNextRoundButton() {
        runTest(testDispatcher) {
            coEvery { startGameUseCase(Unit) } returns listOf(
                Player(
                    pile = listOf(
                        Card(CardFaceName.ACE, CardSuit.CLUBS)
                    )
                ), Player(
                    pile = listOf(
                        Card(CardFaceName.ACE, CardSuit.HEARTS)
                    )
                )
            )
            coEvery { getSuitOrderUseCase(Unit) } returns order
            coEvery { dealTopUseCase(any()) } returns Pair(
                Player(),
                Card(CardFaceName.ACE, CardSuit.CLUBS)
            )
            coEvery { getRoundWinnerUseCase(any()) } returns Card(CardFaceName.ACE, CardSuit.CLUBS)
            coEvery { winRoundUseCase(any()) } returns Player(wins = 1, discardPile = 2)

            viewModel = GameViewModel(
                startGameUseCase,
                winRoundUseCase,
                dealTopUseCase,
                getSuitOrderUseCase,
                getRoundWinnerUseCase,
                getGameWinnerUseCase
            )

            viewModel.nextRound()
        }

        assertEquals(0, viewModel.showWinner.value)
        assertEquals(false, viewModel.showNextRound.value)
    }
}