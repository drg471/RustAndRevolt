package com.drg.rustandrevolt.hilt

import android.app.Application
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import dagger.hilt.android.HiltAndroidApp


//  Activa/Inicializa  HILT
//  Y las SharedPereferences
@HiltAndroidApp
class MyApplication : Application(){
    companion object{
        lateinit var musicPreferences: MusicPreferences
    }

    override fun onCreate() {
        super.onCreate()
        musicPreferences = MusicPreferences(applicationContext)
    }
}