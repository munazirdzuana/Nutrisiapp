package com.munaz.nutrisiapp.ui.isidata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.local.ModelPreferences
import com.munaz.nutrisiapp.data.response.ResepResponse
import com.munaz.nutrisiapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMRiwayat @Inject constructor(private val repo: Repo) : ViewModel() {
    private val _responProfile = MutableLiveData<Resource<Boolean>>()
    val responProfile: LiveData<Resource<Boolean>> = _responProfile

    fun doSaveProfile(profile: ModelPreferences) {
        viewModelScope.launch {
            _responProfile.value = Resource.Loading()
            repo.saveProfile(profile).collect {
                _responProfile.value = it
            }
        }
    }
}