package com.example.composerediexpress

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import org.json.JSONObject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")
class DataStoreManager(private val context: Context) {
    suspend fun saveUserDataStore(user: JSONObject, remember: Int) {
        context.dataStore.edit {
            it[stringPreferencesKey("name")] = user["name"].toString()
            it[stringPreferencesKey("email")] = user["email"].toString()
            it[stringPreferencesKey("phoneNumber")] = user["phoneNumber"].toString()
            it[stringPreferencesKey("password")] = user["password"].toString()
            it[intPreferencesKey("remember")] = remember
        }
    }

    fun loadUserDataStore() = context.dataStore.data.map {
        return@map User(
            it[stringPreferencesKey("name")] ?: "",
            it[stringPreferencesKey("phoneNumber")] ?: "",
            it[stringPreferencesKey("email")] ?: "",
            it[stringPreferencesKey("password")] ?: "",
            )
    }
}