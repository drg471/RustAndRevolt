package com.drg.rustandrevolt.ui.screens.options

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.data.sources.datastore.Vibrator
import com.drg.rustandrevolt.data.sources.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.MusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val musicPlayer: MusicPlayer,
    private val vibrator: Vibrator
) : ViewModel() {

    var isVibration by mutableStateOf(false)
        private set

    init{
        viewModelScope.launch(){
            isVibration = vibrator.isVibrationOn()!!
        }
    }

    fun buttonSound(){
        if (MusicPreferences.isMusicEnabledCompanion){
            musicPlayer.playFX(FxButtons.FxButton1)
        }
    }

    fun activateVibration(activate: Boolean) {
        viewModelScope.launch(){
            vibrator.setVibrationOn(activate)
            isVibration = vibrator.isVibrationOn()!!
        }
    }

}