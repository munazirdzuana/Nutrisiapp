package com.munaz.nutrisiapp.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.response.LoginResponse
import com.munaz.nutrisiapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class VMss @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    private val _token = MutableLiveData<Flow<String?>>()
    val token: LiveData<Flow<String?>> get() = _token

    fun doGetToken(){
        viewModelScope.launch {
            repo.getToken().collect {
                _token.value=it
            }
        }
    }


}