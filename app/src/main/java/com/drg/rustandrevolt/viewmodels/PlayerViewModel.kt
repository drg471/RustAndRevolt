package com.drg.rustandrevolt.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FX
import com.drg.rustandrevolt.sound.MusicPlayer
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

    lateinit var context: Context

    init {
        if (playerUseCase.getPlayer() == null){
            mutablePlayerName = ""
            mutablePlayerScore = ""
        }else{
            mutablePlayerName = playerUseCase.getPlayer().name
            mutablePlayerScore = playerUseCase.getPlayer().score.toString()
        }
    }

    fun buttonSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            val musicPlayer = MusicPlayer(context)
            musicPlayer.playFX(FX.FxButton1)
        }
    }
}