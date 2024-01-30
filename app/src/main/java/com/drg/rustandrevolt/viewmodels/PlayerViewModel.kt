package com.drg.rustandrevolt.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.usecases.PlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    playerUseCase: PlayerUseCase
) : ViewModel() {

    var mutablePlayerName by mutableStateOf("")
        private set
    var mutablePlayerScore by mutableStateOf("")
        private set

    init {
        mutablePlayerName = playerUseCase.getPlayer().name
        mutablePlayerScore = playerUseCase.getPlayer().score.toString()
    }
}