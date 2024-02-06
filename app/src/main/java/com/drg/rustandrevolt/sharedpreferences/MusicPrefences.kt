package com.drg.rustandrevolt.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.sound.MusicPlayer
import javax.inject.Inject
import javax.inject.Singleton

class MusicPreferences (val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MusicPrefs", 0)

    var isMusicEnabled: Boolean by mutableStateOf(sharedPreferences.getBoolean("isMusicEnabled", false))
        private set

    val musicPlayer: MusicPlayer = MusicPlayer(context)

    init{
        setMusicEnabledPrefs(isMusicEnabled, true)
    }

    fun setMusicEnabledPrefs(enabled: Boolean, inMenu: Boolean) {
        isMusicEnabled = enabled
        sharedPreferences.edit().putBoolean("isMusicEnabled", enabled).apply()

        if (isMusicEnabled){
            musicPlayer.play(inMenu)
            //musicPlayer.play(R.raw.musicmenu1)
        }
        else{
            musicPlayer.stop()
        }
    }
}