package com.alagunas.highestcard.di

import com.alagunas.highestcard.ui.screens.game.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel { GameViewModel(get(), get(), get(), get(), get(), get()) }
}