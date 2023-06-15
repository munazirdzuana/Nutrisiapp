package com.munaz.nutrisiapp.data.local

import android.content.Context
import android.graphics.ColorSpace.Model
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.error.DEFAULT_ERROR
import com.munaz.nutrisiapp.utils.DataStore_Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


//val Context.Profile : DataStore<Preferences> by  preferencesDataStore(name = DataStore_Profile)
class DataStoreProfile (private val context: Context){

//    suspend fun saveProfile(user: ModelPreferences) : Resource<Boolean> {
//        return try {
//            context.Profile.edit { preferences ->
//                preferences[Key_Email] = user.email
//                preferences[Key_NAME] = user.name
//                preferences[Key_USIA] = user.beratBadan
//                preferences[Key_BB] = user.beratBadan
//                preferences[Key_TB] = user.tinggiBadan
//                preferences[Key_JK] = user.jenisKelamin
//                preferences[Key_Alergi] = user.alergi
//                preferences[Key_Riwayat] = user.penyakit
//                preferences[Key_AKTIVITAS] = user.aktivitas
//                preferences[Key_STRESS] = user.stress
//            }
//            Resource.Success(true)
//        } catch (e: Exception) {
//            Resource.DataError(DEFAULT_ERROR)
//        }
//    }
//
//    suspend fun getProfile(): ModelPreferences {
//        val dataStore = context.Profile
//        val preferences = dataStore.data.first()
//        val email=preferences[Key_Email]?: ""
//        val name= preferences[Key_NAME]?: ""
//        val usia= preferences[Key_USIA]?: 0
//        val berat= preferences[Key_BB]?: 0
//        val tinggi= preferences[Key_TB]?: 0
//        val kelamin= preferences[Key_JK]?: ""
//        val alergi= preferences[Key_Alergi]?: ""
//        val riwayat = preferences[Key_Riwayat]?: ""
//        val aktivitas= preferences[Key_AKTIVITAS]?: ""
//        val stress = preferences[Key_STRESS]?: ""
//
//        return ModelPreferences(email,name,usia,berat,tinggi,kelamin,alergi,riwayat,aktivitas,stress)
//    }

    suspend fun logout() {
        context.dataToken.edit { preferences ->
            preferences.clear()
        }
    }


    companion object {
//        private val Key_Email = stringPreferencesKey("email")
//        private val Key_NAME = stringPreferencesKey("name")
//        private val Key_USIA = intPreferencesKey("age")
//        private val Key_BB = intPreferencesKey("berat")
//        private val Key_TB = intPreferencesKey("tinggi")
//        private val Key_JK = stringPreferencesKey("jenisk")
//        private val Key_Alergi = stringPreferencesKey("alergi")
//        private val Key_Riwayat = stringPreferencesKey("Penyakit")
//        private val Key_AKTIVITAS = stringPreferencesKey("aktivitas")
//        private val Key_STRESS = stringPreferencesKey("stress")
    }
}