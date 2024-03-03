package com.drg.rustandrevolt.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.retrofit.RetrofitFraseServiceFactory
import com.drg.rustandrevolt.retrofit.RetrofitMoviesService
import com.drg.rustandrevolt.retrofit.RetrofitPhraseService
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.MusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val musicPlayer: MusicPlayer,
) : ViewModel() {
    @Inject
    lateinit var retrofitMoviesService: RetrofitMoviesService

    fun initRetrofit(){
        viewModelScope.launch() {
            //RETROFIT Movies
            val movies = retrofitMoviesService.listPopularMovies("5d7c3467997b5cebca56a598a9a37d67", "US")
            var iter: Int = 1
            for (movie in movies.results){
                Log.i("movie top ${iter}", movie.original_title)
                iter++
            }
        }
    }

    fun buttonSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxButtons.FxButton1)
        }
    }
}