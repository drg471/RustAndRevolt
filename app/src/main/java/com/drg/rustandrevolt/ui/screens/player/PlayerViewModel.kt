package com.drg.rustandrevolt.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.data.sources.network.RetrofitPhraseService
import com.drg.rustandrevolt.data.sources.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.MusicPlayer
import com.drg.rustandrevolt.domain.usecases.PlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    playerUseCase: PlayerUseCase,
    private val musicPlayer: MusicPlayer
) : ViewModel() {

    @Inject
    lateinit var retrofitPhraseService: RetrofitPhraseService

    private val _statePlayer = MutableStateFlow<PlayerState>(PlayerState.LoadingPlayer)
    val statePlayer: StateFlow<PlayerState> = _statePlayer

    private val _statePhraseOfDay = MutableStateFlow<PhraseOfDayState>(PhraseOfDayState.LoadingPhraseOfDay)
    val statePhraseOfDay : StateFlow<PhraseOfDayState> = _statePhraseOfDay

    var phraseOfDay: String = ""
    var mutablePlayerName: String = ""
    var mutablePlayerScore: String = ""

    init {
        mutablePlayerName = playerUseCase.getPlayer().name
        mutablePlayerScore = playerUseCase.getPlayer().score.toString()

        _statePlayer.value = PlayerState.SuccesPlayer(mutablePlayerName, mutablePlayerScore)
    }

    fun buttonSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxButtons.FxButton1)
        }
    }

    fun initRetrofit(){
        viewModelScope.launch() {
            //RETROFIT PhraseOfDay
            phraseOfDay = retrofitPhraseService.getPhraseOfDay().phrase
            _statePhraseOfDay.value = PhraseOfDayState.SuccesPhraseOfDay(phraseOfDay)
        }
    }
}

sealed class PlayerState{
    object LoadingPlayer: PlayerState()
    data class SuccesPlayer(
        val playerName: String,
        val playerScore: String
    ): PlayerState()
}

sealed class PhraseOfDayState{
    object LoadingPhraseOfDay: PhraseOfDayState()
    data class SuccesPhraseOfDay(
        val phraseOfDay: String
    ): PhraseOfDayState()
}