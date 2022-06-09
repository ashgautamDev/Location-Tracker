package com.ashish.locationtracker.data.manager

import android.content.Context
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {

    private val dataStore = context.createDataStore(name = "user_prefs")
    companion object {
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
        val USER_NUMBER_KEY = preferencesKey<Int>("USER_NUMBER")
    }

    suspend fun storeUser(number: Int, name: String) {
        dataStore.edit {
            it[USER_NUMBER_KEY] = number
            it[USER_NAME_KEY] = name

        }
    }

    fun isKeyStored(key: Preferences.Key<String>): Flow<Boolean>  = dataStore.data.map {
                preference -> preference.contains(key)
        }

    val userNumberFlow: Flow<Int> = dataStore.data.map {
        it[USER_NUMBER_KEY] ?: 0
    }

    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }
}