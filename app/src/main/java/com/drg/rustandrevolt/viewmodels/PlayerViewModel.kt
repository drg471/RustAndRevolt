package com.drg.rustandrevolt.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.retrofit.RetrofitPhraseService
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.MusicPlayer
import com.drg.rustandrevolt.usecases.PlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    playerUseCase: PlayerUseCase
) : ViewModel() {

    @Inject
    lateinit var retrofitPhraseService: RetrofitPhraseService
    var phraseOfDay by mutableStateOf("")

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
            musicPlayer.playFX(FxButtons.FxButton1)
        }
    }

    fun initRetrofit(){
        viewModelScope.launch() {
            //RETROFIT PhraseOfDay
            phraseOfDay = retrofitPhraseService.getPhraseOfDay().phrase
        }
    }
}