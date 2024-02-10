package com.drg.rustandrevolt.hilt

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import com.drg.rustandrevolt.domain.Player
import com.drg.rustandrevolt.repositories.PlayerRepository
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sqlite.PlayerDBScheme
import com.drg.rustandrevolt.sqlite.PlayerDbHelper
import com.drg.rustandrevolt.sqlite.PlayerDao
import dagger.hilt.android.HiltAndroidApp



//  Activa/Inicializa  HILT
//  Y las SharedPereferences
//  Y las DataSTore

val Context.dataStore by preferencesDataStore(name = "CHARACTER_NAME_SELECTED")

@HiltAndroidApp
class MyApplication : Application(){
    companion object{
        lateinit var musicPreferences: MusicPreferences
    }

    override fun onCreate() {
        super.onCreate()
        musicPreferences = MusicPreferences(applicationContext)
        PlayerRepository.context = this
        //**************************************
    }
}