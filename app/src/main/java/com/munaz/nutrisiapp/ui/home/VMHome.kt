package com.munaz.nutrisiapp.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.local.ModelPreferences
import com.munaz.nutrisiapp.data.request.RekomendasiReq
import com.munaz.nutrisiapp.data.response.*
import com.munaz.nutrisiapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMHome @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    private val _responListArtikel = MutableLiveData<Resource<ArtikelResponse>>()
    val responListArtikel: LiveData<Resource<ArtikelResponse>> get() = _responListArtikel

    private val _responfood = MutableLiveData<Resource<ResepResponse>>()
    val responfood: LiveData<Resource<ResepResponse>> get() = _responfood

    private val _responProfile = MutableLiveData<Resource<ModelPreferences>>()
    val profile: LiveData<Resource<ModelPreferences>> get() = _responProfile

    private val _responRekomendasi = MutableLiveData<Resource<RecomendasiResponseX>>()
    val responseRekomendasi: LiveData<Resource<RecomendasiResponseX>> get() = _responRekomendasi

    private val _showErr = MutableLiveData<String>()
    val showErr: LiveData<String> get() = _showErr

    fun getArtikel() {
        viewModelScope.launch {
            _responListArtikel.value = Resource.Loading()
            repo.doGetListArtikel().collect {
                Log.d(ContentValues.TAG, it.toString())
                _responListArtikel.value = it
            }
        }
    }

    fun getFoodResep(page: Int, limit: Int) {
        viewModelScope.launch {
            _responfood.value = Resource.Loading()
            repo.doGetFoodResep(page, limit).collect {
                Log.d(ContentValues.TAG, it.toString())
                _responfood.value = it
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            _responProfile.value = Resource.Loading()
            repo.getProfile().collect {
                Log.d(ContentValues.TAG, it.toString())
                _responProfile.value = it
            }
        }
    }

    fun getRecomendasi(rekomendasiReq: RekomendasiReq
    ) {
        viewModelScope.launch {
            Log.d(ContentValues.TAG, "sampe vm : $rekomendasiReq")
            repo.doGetRecomendasi(rekomendasiReq).collect {
                _responRekomendasi.value = it
            }
        }
    }

}