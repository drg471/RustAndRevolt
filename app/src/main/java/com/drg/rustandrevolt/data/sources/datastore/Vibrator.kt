package com.drg.rustandrevolt.data.sources.datastore

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.drg.rustandrevolt.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Vibrator @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val dataStore = context.dataStore
    private val KEY_NAME = "vibration"
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    fun start(durationMiliSeconds: Long) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        coroutineScope.launch(Dispatchers.IO) {
            if (isVibrationOn()!!){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //vibrator.vibrate(VibrationEffect.createOneShot(durationMiliSeconds, VibrationEffect.DEFAULT_AMPLITUDE))

                    // Define un patrón de vibración intermitente (ejemplo: 1 segundo encendido, 0.5 segundos apagado)
                    val pattern = longArrayOf(150, 50, 150, 100, 500, 200)

                    // Crea el efecto de vibración con el patrón intermitente
                    vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
                } else {
                    vibrator.vibrate(durationMiliSeconds)
                }
            }
        }

    }

    suspend fun setVibrationOn(activate: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(KEY_NAME)]= activate
        }
    }

    suspend fun isVibrationOn(): Boolean? {
        val preferences = dataStore.data.first()
        val preferencesKey = booleanPreferencesKey(KEY_NAME)

        if (preferences[preferencesKey] == null) return true
        return preferences[preferencesKey]
    }
}