package com.munaz.nutrisiapp.data.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.request.LoginReq
import com.munaz.nutrisiapp.data.request.RegisReq
import com.munaz.nutrisiapp.data.request.RekomendasiReq
import com.munaz.nutrisiapp.data.response.*
import com.munaz.nutrisiapp.error.NETWORK_ERROR
import com.munaz.nutrisiapp.error.NO_INTERNET_CONNECTION
import com.munaz.nutrisiapp.utils.NetworkConnectivity
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val ApiConfig: ApiConfig, private val networkConnectivity: NetworkConnectivity
) : DataResource {

    private suspend fun proses(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }


    override suspend fun register(regisReq: RegisReq): Resource<LoginResponse> {
        val recipesService = ApiConfig.createService(ApiService::class.java)
        return when (val response = proses { recipesService.register(regisReq) }) {
            is LoginResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun login(loginReq: LoginReq): Resource<LoginResponse> {
        val recipesService = ApiConfig.createService(ApiService::class.java)
        return when (val response = proses { recipesService.login(loginReq) }) {
            is LoginResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun listArikel(): Resource<ArtikelResponse> {
        val recipesService = ApiConfig.createService(ApiService::class.java)
        return when (val response = proses { recipesService.artikelList() }) {
            is ArtikelResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun getFood(page: Int, limit: Int): Resource<ResepResponse> {
        val recipesService = ApiConfig.createService(ApiService::class.java)
        return when (val response = proses { recipesService.resepList(page, limit) }) {
            is ResepResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun postImage(file: MultipartBody.Part): Resource<ImageResponse> {
        val recipesService = ApiConfig.createService2(ApiService::class.java)
        return when (val response = proses { recipesService.image(file) }) {
            is ImageResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun getRecomendasi(
        rekomendasiReq: RekomendasiReq
    ): Resource<RecomendasiResponseX> {
        val recipesService = ApiConfig.createService3(ApiService::class.java)
        return when (val response =
            proses { recipesService.getrecomend(rekomendasiReq) }) {
            is RecomendasiResponseX -> {
                Log.d(TAG,"tes $response")
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.errorBody()?.string()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

}