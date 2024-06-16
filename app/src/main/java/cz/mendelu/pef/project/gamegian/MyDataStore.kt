package cz.mendelu.pef.project.gamegian

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyDataStore(private val dataStore: DataStore<Preferences>) {

    private object PreferenceKeys {
        val gender: Preferences.Key<Boolean> = booleanPreferencesKey("gender")
        val username: Preferences.Key<String> = stringPreferencesKey("username")
        val token: Preferences.Key<String> = stringPreferencesKey("token")
        val time: Preferences.Key<Long> = longPreferencesKey("time")
    }

    // Update methods
    suspend fun updateGender(isMale: Boolean) = dataStore.edit { preferences ->
        preferences[PreferenceKeys.gender] = isMale
    }

    suspend fun updateUsername(username: String) = dataStore.edit { preferences ->
        preferences[PreferenceKeys.username] = username
    }

    suspend fun updateToken(token: String) = dataStore.edit { preferences ->
        preferences[PreferenceKeys.token] = token
    }

    suspend fun updateTime(time: Long) = dataStore.edit { preferences ->
        preferences[PreferenceKeys.time] = time
    }

    // Append method
    suspend fun appendTime(newTime: Long) = dataStore.edit { preferences ->
        val currentTime = preferences[PreferenceKeys.time] ?: 0L
        preferences[PreferenceKeys.time] = currentTime + newTime
    }

    // Watch methods
    fun watchGender(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[PreferenceKeys.gender] ?: false
    }

    fun watchUsername(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[PreferenceKeys.username]
    }

    fun watchToken(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[PreferenceKeys.token]
    }

    fun watchTime(): Flow<Long?> = dataStore.data.map { preferences ->
        preferences[PreferenceKeys.time]
    }
}
