package cz.mendelu.pef.project.gamegian

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("example")

class MyDataStore(val context: Context) {
    private object PreferenceKeys {
        val gender: Preferences.Key<Boolean> = booleanPreferencesKey("gender")
        val username: Preferences.Key<String> = stringPreferencesKey("username")
        val token: Preferences.Key<String> = stringPreferencesKey("token")
        val time: Preferences.Key<Long> = longPreferencesKey("time")
    }

    // Update methods
    suspend fun updateGender(isMale: Boolean) = context.dataStore.edit { preferences ->
        preferences[PreferenceKeys.gender] = isMale
    }

    suspend fun updateUsername(username: String) = context.dataStore.edit { preferences ->
        preferences[PreferenceKeys.username] = username
    }

    suspend fun updateToken(token: String) = context.dataStore.edit { preferences ->
        preferences[PreferenceKeys.token] = token
    }
    suspend fun updateTime(time: Long) = context.dataStore.edit { preferences ->
        preferences[PreferenceKeys.time] = time
    }

    // Watch methods
    fun watchGender(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.gender] ?: false
    }

    fun watchUsername(): Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.username]
    }

    fun watchToken(): Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.token]
    }

    fun watchTime(): Flow<Long?> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.time]
    }
}
