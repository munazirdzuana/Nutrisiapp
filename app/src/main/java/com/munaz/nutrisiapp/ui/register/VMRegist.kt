package com.munaz.nutrisiapp.ui.register

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.request.RegisReq
import com.munaz.nutrisiapp.data.response.LoginResponse
import com.munaz.nutrisiapp.error.*
import com.munaz.nutrisiapp.repo.Repo
import com.munaz.nutrisiapp.usecase.error.ErrorManager
import com.munaz.nutrisiapp.utils.RegexEmail.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMRegist @Inject constructor(
    private val dataRepository: Repo
) : ViewModel() {
    @Inject
    lateinit var errorManager: ErrorManager

    private val _response = MutableLiveData<Resource<LoginResponse>>()
    val response: LiveData<Resource<LoginResponse>> get() = _response

    private val _showErr = MutableLiveData<String>()
    val showErr: LiveData<String> get() = _showErr

    private val _savetoken = MutableLiveData<Resource<Boolean>>()
    val savtoken: LiveData<Resource<Boolean>> get() = _savetoken

    fun doregis(regisReq: RegisReq) {
        val isEmailValid = isValidEmail(regisReq.email)
        val isPassWordValid =
            regisReq.password.trim().length >= 8 && regisReq.password == regisReq.passwordConfirm
        if (isEmailValid && !isPassWordValid) {
            _response.value = Resource.DataError(PASS_WORD_ERROR)
        } else if (!isEmailValid && isPassWordValid) {
            _response.value = Resource.DataError(USER_NAME_ERROR)
        } else if (!isEmailValid && !isPassWordValid) {
            _response.value = Resource.DataError(CHECK_YOUR_FIELDS)
        } else {
            viewModelScope.launch {
                _response.value = Resource.Loading()
                dataRepository.doRegister(regisReq).collect {
                    _response.value = it
                }
            }
        }
    }

    fun showErrMessage(errorCode: Int) {
        Log.d(TAG, errorCode.toString())
        val error = errorManager.getError(errorCode)
        Log.d(TAG, error.description)
        _showErr.value = error.description
    }
    fun doSaveToken() {
        viewModelScope.launch {
            _savetoken.value = Resource.Loading()
            _response.value?.data?.token.let { s ->
                if (s != null) {
                    dataRepository.saveToken(s).collect {
                        Log.d(TAG, "Token :$s")
                        _savetoken.value = it
                    }
                }
            }

        }
    }
}