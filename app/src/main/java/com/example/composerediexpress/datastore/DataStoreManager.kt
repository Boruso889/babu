package com.example.composerediexpress.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.composerediexpress.User
import kotlinx.coroutines.flow.map
import org.json.JSONObject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
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


    suspend fun saveOriginDetails(details: OriginDetails) {
        context.dataStore.edit {
            it[stringPreferencesKey("origin_address")] = details.address
            it[stringPreferencesKey("origin_state")] = details.state
            it[stringPreferencesKey("origin_phoneNumber")] = details.phoneNumber
            it[stringPreferencesKey("origin_other")] = details.other
        }
    }

    fun loadOriginDetails() = context.dataStore.data.map {
        return@map OriginDetails(
            it[stringPreferencesKey("origin_address")] ?: "",
            it[stringPreferencesKey("origin_state")] ?: "",
            it[stringPreferencesKey("origin_phoneNumber")] ?: "",
            it[stringPreferencesKey("origin_other")] ?: "",
        )
    }

    suspend fun saveDestinationDetails(details: DestinationDetails) {
        context.dataStore.edit {
            it[stringPreferencesKey("destination_address")] = details.address
            it[stringPreferencesKey("destination_state")] = details.state
            it[stringPreferencesKey("destination_phoneNumber")] = details.phoneNumber
            it[stringPreferencesKey("destination__other")] = details.other
        }
    }

    fun loadDestinationDetails() = context.dataStore.data.map {
        return@map DestinationDetails(
            it[stringPreferencesKey("destination_address")] ?: "",
            it[stringPreferencesKey("destination_state")] ?: "",
            it[stringPreferencesKey("destination_phoneNumber")] ?: "",
            it[stringPreferencesKey("destination__other")] ?: "",
        )
    }

    suspend fun savePackageDetails(details: PackageDetails) {
        context.dataStore.edit {
            it[stringPreferencesKey("package_items")] = details.items
            it[stringPreferencesKey("package_weight")] = details.weight
            it[stringPreferencesKey("package_worth")] = details.worth
        }
    }

    fun loadPackageDetails() = context.dataStore.data.map {
        return@map PackageDetails(
            it[stringPreferencesKey("package_items")] ?: "",
                    it[stringPreferencesKey("package_weight")] ?: "",
                    it[stringPreferencesKey("package_worth")] ?: "",
        )
    }
}