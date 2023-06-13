package com.munaz.nutrisiapp.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munaz.nutrisiapp.data.Resource
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
    val responListArtikel: LiveData<Resource<ArtikelResponse>>get() = _responListArtikel

    private val _responfood = MutableLiveData<Resource<ResepResponse>>()
    val responfood: LiveData<Resource<ResepResponse>>get() = _responfood

    private val _showErr = MutableLiveData<String>()
    val showErr: LiveData<String> get() = _showErr

    fun getArtikel() {
        viewModelScope.launch {
            _responListArtikel.value = Resource.Loading()
            repo.doGetListArtikel().collect{
                Log.d(ContentValues.TAG, it.toString())
                _responListArtikel.value=it
            }
        }
    }

    fun getFoodResep(page : Int, limit : Int) {
        viewModelScope.launch {
            _responfood.value = Resource.Loading()
            repo.doGetFoodResep(page, limit).collect{
                Log.d(ContentValues.TAG, it.toString())
                _responfood.value=it
            }
        }
    }

}