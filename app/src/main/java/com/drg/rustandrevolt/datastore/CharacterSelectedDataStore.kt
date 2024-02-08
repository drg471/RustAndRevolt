package com.drg.rustandrevolt.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.drg.rustandrevolt.hilt.dataStore
import com.drg.rustandrevolt.service.AppContextSingleton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterSelectedDataStore @Inject constructor() {
    private val dataStore = AppContextSingleton.getContext().dataStore
    private val KEY_NAME = "name"

    suspend fun saveCharacterName(name: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(KEY_NAME)] = name
        }
    }

    suspend fun getCharacterName(): String {
        val preferences = dataStore.data.first()
        val preferencesKey = stringPreferencesKey(KEY_NAME)
        return preferences[preferencesKey]!!
    }

    suspend fun resetDataStore() {
        if (isDataStored()) {
            dataStore.edit { preferences ->
                preferences.remove(stringPreferencesKey(KEY_NAME))
            }
        }
    }

    private suspend fun isDataStored(): Boolean {
        val preferences = dataStore.data.first()
        val preferencesKey = stringPreferencesKey(KEY_NAME)
        return preferences.contains(preferencesKey)
    }
}