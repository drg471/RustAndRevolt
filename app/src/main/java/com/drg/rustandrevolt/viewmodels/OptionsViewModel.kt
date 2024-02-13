package com.drg.rustandrevolt.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.retrofit.RetrofitService
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FX
import com.drg.rustandrevolt.sound.MusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor() : ViewModel() {

    lateinit var context: Context

    fun buttonSound(){
        if (MusicPreferences.isMusicEnabledCompanion){
            val musicPlayer = MusicPlayer(context)
            musicPlayer.playFX(FX.FxButton1)
        }
    }

}