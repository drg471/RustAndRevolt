package com.drg.rustandrevolt.data.sources.datastore

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.drg.rustandrevolt.dataStore
import com.drg.rustandrevolt.domain.service.AppContextSingleton
import kotlinx.coroutines.flow.first
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