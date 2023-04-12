package com.alagunas.usecases.di

import com.alagunas.usecases.game.DealTopUseCase
import com.alagunas.usecases.game.StartGameUseCase
import com.alagunas.usecases.game.WinRoundUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory { StartGameUseCase() }
    factory { WinRoundUseCase() }
    factory { DealTopUseCase() }
}