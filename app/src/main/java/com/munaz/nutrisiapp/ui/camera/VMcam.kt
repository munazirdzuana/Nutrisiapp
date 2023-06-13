package com.munaz.nutrisiapp.ui.camera

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.response.ImageResponse
import com.munaz.nutrisiapp.data.response.ResepResponse
import com.munaz.nutrisiapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Multipart
import java.io.File
import javax.inject.Inject

@HiltViewModel
class VMcam @Inject constructor(private val repo: Repo) : ViewModel() {

    private val _responScan = MutableLiveData<Resource<ImageResponse>>()
    val responScan: LiveData<Resource<ImageResponse>> get() = _responScan

    fun doImage(file : File){
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestImageFile
        )
        viewModelScope.launch {
            _responScan.value = Resource.Loading()
            repo.doPostImage(imageMultipart).collect{
                Log.d(ContentValues.TAG, it.toString())
                _responScan.value=it
            }
        }
    }
}