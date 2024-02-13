package com.drg.rustandrevolt.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.MusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor() : ViewModel() {

    lateinit var context: Context

    fun buttonSound(){
        if (MusicPreferences.isMusicEnabledCompanion){
            val musicPlayer = MusicPlayer(context)
            musicPlayer.playFX(FxButtons.FxButton1)
        }
    }

}