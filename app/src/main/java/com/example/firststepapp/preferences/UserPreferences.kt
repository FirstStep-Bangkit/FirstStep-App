package com.example.firststepapp.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val token = stringPreferencesKey("token")
    private val email = stringPreferencesKey("email")
    private val name = stringPreferencesKey("name")
    private val username = stringPreferencesKey("username")

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null
        const val preferenceDefaultValue = "Null"
        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    fun getToken(): Flow<String> = dataStore.data.map { it[token] ?: preferenceDefaultValue }
    fun getEmail(): Flow<String> = dataStore.data.map { it[email] ?: preferenceDefaultValue }
    fun getName(): Flow<String> = dataStore.data.map { it[name] ?: preferenceDefaultValue }
    fun getUsername() : Flow<String> = dataStore.data.map { it[username] ?: preferenceDefaultValue }

    suspend fun saveLoginSession(token: String, email: String, name:String, username: String) {
        dataStore.edit { preferences ->
            preferences[this.token] = token
            preferences[this.email] = email
            preferences[this.name] = name
            preferences[this.username] = username
        }
        Log.d("UserPreferences", "Saved login session: token=$token, email=$email, name=$name, username=$username")
    }

    suspend fun clearLoginSession() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}
