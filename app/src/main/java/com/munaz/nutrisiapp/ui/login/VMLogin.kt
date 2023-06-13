package com.munaz.nutrisiapp.ui.login

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.local.Tokenmodel
import com.munaz.nutrisiapp.data.request.LoginReq
import com.munaz.nutrisiapp.data.response.LoginResponse
import com.munaz.nutrisiapp.error.CHECK_YOUR_FIELDS
import com.munaz.nutrisiapp.error.PASS_WORD_ERROR
import com.munaz.nutrisiapp.error.USER_NAME_ERROR
import com.munaz.nutrisiapp.repo.Repo
import com.munaz.nutrisiapp.usecase.error.ErrorManager
import com.munaz.nutrisiapp.utils.RegexEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMLogin @Inject constructor(
    private val dataRepository: Repo, private val errorManager: ErrorManager
) : ViewModel() {
    private val _response = MutableLiveData<Resource<LoginResponse>>()
    val response: LiveData<Resource<LoginResponse>> get() = _response

    private val _savetoken = MutableLiveData<Resource<Boolean>>()
    val savtoken: LiveData<Resource<Boolean>> get() = _savetoken

    private val _showErr = MutableLiveData<String>()
    val showErr: LiveData<String> get() = _showErr

    fun doLogin(loginReq: LoginReq) {
        val isEmailValid = RegexEmail.isValidEmail(loginReq.email)
        val isPassWordValid = loginReq.password.trim().length > 8
        if (isEmailValid && !isPassWordValid) {
            _response.value = Resource.DataError(PASS_WORD_ERROR)
        } else if (!isEmailValid && isPassWordValid) {
            _response.value = Resource.DataError(USER_NAME_ERROR)
        } else if (!isEmailValid && !isPassWordValid) {
            _response.value = Resource.DataError(CHECK_YOUR_FIELDS)
        } else {
            viewModelScope.launch {
                _response.value = Resource.Loading()
                dataRepository.doLogin(loginReq).collect {
                    _response.value = it
                }
            }
        }
    }

    fun doSaveToken() {
        viewModelScope.launch {
            _savetoken.value = Resource.Loading()
            _response.value?.data?.token.let { s ->
                if (s != null) {
                    dataRepository.saveToken(s).collect {
                        _savetoken.value = it
                    }
                }
            }

        }
    }

    fun showErrMessage(errorCode: Int) {
        Log.d(ContentValues.TAG, errorCode.toString())
        val error = errorManager.getError(errorCode)
        Log.d(ContentValues.TAG, error.description)
        _showErr.value = error.description
    }

}