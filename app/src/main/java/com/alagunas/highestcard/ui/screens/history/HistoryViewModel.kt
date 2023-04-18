package com.alagunas.highestcard.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alagunas.data.repositories.GameRepository
import com.alagunas.domain.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(gameRepository: GameRepository): ViewModel() {

    private val _showGames: MutableStateFlow<List<Game>> = MutableStateFlow(listOf())
    val showGames: StateFlow<List<Game>> = _showGames.asStateFlow()


    init {
        viewModelScope.launch {
            _showGames.value = gameRepository.getGames()
        }
    }
}