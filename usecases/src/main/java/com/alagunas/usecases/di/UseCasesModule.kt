package com.alagunas.usecases.di

import com.alagunas.usecases.game.*
import com.alagunas.usecases.game.getroundwinner.GetRoundWinnerUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory { StartGameUseCase() }
    factory { WinRoundUseCase() }
    factory { DealTopUseCase() }
    factory { GetSuitOrderUseCase() }
    factory { GetRoundWinnerUseCase() }
    factory { GameWinnerUseCase() }
}