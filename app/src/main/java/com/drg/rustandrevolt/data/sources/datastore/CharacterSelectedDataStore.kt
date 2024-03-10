package com.drg.rustandrevolt.data.sources.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.drg.rustandrevolt.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterSelectedDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore
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