package com.munaz.nutrisiapp.ui.rekomendMakan

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.local.ModelPreferences
import com.munaz.nutrisiapp.data.response.LoginResponse
import com.munaz.nutrisiapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMRekomendasi @Inject constructor(
    private val repo:Repo
) :ViewModel() {
    private val _responProfile = MutableLiveData<Resource<ModelPreferences>>()
    val profile: LiveData<Resource<ModelPreferences>>get() = _responProfile


    fun getProfile() {
        viewModelScope.launch {
            _responProfile.value = Resource.Loading()
            repo.getProfile().collect{
                Log.d(ContentValues.TAG, it.toString())
                _responProfile.value=it
            }
        }
    }
}