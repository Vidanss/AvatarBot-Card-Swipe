package com.example.businesscard.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreInstance {
    private val Context.dataStore by preferencesDataStore(name = "settings")

    val BOOLEAN_KEY = booleanPreferencesKey("boolean_key")
    val YEAR_KEY = intPreferencesKey("int_key")
    val NAME_KEY = stringPreferencesKey("name_key")
    val ROLE_KEY = stringPreferencesKey("role_key")
    val PHONE_KEY = stringPreferencesKey("phone_key")
    val HANDLE_KEY = stringPreferencesKey("handle_key")
    val EMAIL_KEY = stringPreferencesKey("email_key")

    suspend fun saveBooleanPreferences(context: Context, key: Preferences.Key<Boolean>, value: Boolean) {
        context.dataStore.edit { preferences -> preferences[key] = value }
    }

    suspend fun saveStringPreferences(context: Context, key: Preferences.Key<String>, value: String) {
        context.dataStore.edit { preferences -> preferences[key] = value }
    }

    suspend fun saveIntPreferences(context: Context, key: Preferences.Key<Int>, value: Int) {
        context.dataStore.edit { preferences -> preferences[key] = value }
    }

    fun getBooleanPreferences(context: Context, key: Preferences.Key<Boolean>): Flow<Boolean?> =
        context.dataStore.data.map { it[key] }

    fun getStringPreferences(context: Context, key: Preferences.Key<String>): Flow<String?> =
        context.dataStore.data.map { it[key] }

    fun getIntPreferences(context: Context, key: Preferences.Key<Int>): Flow<Int?> =
        context.dataStore.data.map { it[key] }
}


