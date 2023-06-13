package com.munaz.nutrisiapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.error.DEFAULT_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


const val DataStore_NAME = "TOKEN"

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DataStore_NAME)

class dataStoreApp (private val context: Context) {

    fun getTOKEN(): Flow<String?>{
        return context.datastore.data.map { preferences ->
            preferences[TOKEN]
        }

    }

    suspend fun saveToken(user: String) : Resource<Boolean> {
        return try {
            context.datastore.edit { preferences ->
                preferences[TOKEN] = user
            }
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.DataError(DEFAULT_ERROR)
        }
    }

    suspend fun logout() {
        context.datastore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val TOKEN = stringPreferencesKey("token")
    }
}


