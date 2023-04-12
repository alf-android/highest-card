package com.alagunas.usecases.di

import com.alagunas.usecases.game.StartGameUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory { StartGameUseCase() }
}