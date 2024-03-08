package com.drg.rustandrevolt.data.sources.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.drg.rustandrevolt.sound.MusicPlayer

class MusicPreferences (val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MusicPrefs", 0)

    companion object{
        var isMusicEnabledCompanion: Boolean = false
    }

    var isMusicEnabled: Boolean by mutableStateOf(sharedPreferences.getBoolean("isMusicEnabled", true))
        private set

    val musicPlayer: MusicPlayer = MusicPlayer(context)

    init{
        setMusicEnabledPrefs(isMusicEnabled, true)
        isMusicEnabledCompanion = isMusicEnabled
    }

    fun setMusicEnabledPrefs(enabled: Boolean, inMenu: Boolean) {
        isMusicEnabled = enabled
        sharedPreferences.edit().putBoolean("isMusicEnabled", enabled).apply()

        if (isMusicEnabled){
            musicPlayer.play(inMenu)
        }
        else{
            musicPlayer.stop()
        }

        isMusicEnabledCompanion = isMusicEnabled
    }
}