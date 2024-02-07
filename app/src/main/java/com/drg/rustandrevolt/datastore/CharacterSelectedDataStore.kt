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

    suspend fun saveCharacterName(name: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("name")] = name
        }
    }

    suspend fun getCharacterName(): String {
        val preferences = dataStore.data.first()
        val preferencesKey = stringPreferencesKey("name")
        return preferences[preferencesKey]!!
    }

    suspend fun resetDataStore() {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey("name"))
        }
    }
}