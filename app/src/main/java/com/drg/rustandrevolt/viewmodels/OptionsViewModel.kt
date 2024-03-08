package com.drg.rustandrevolt.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.datastore.Vibrator
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.MusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val vibrator: Vibrator
) : ViewModel() {

    lateinit var context: Context
    var isVibration by mutableStateOf(false)
        private set

    init{
        viewModelScope.launch(){
            isVibration = vibrator.isVibrationOn()!!
        }
    }

    fun buttonSound(){
        if (MusicPreferences.isMusicEnabledCompanion){
            val musicPlayer = MusicPlayer(context)
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