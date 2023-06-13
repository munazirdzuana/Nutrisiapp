package com.munaz.nutrisiapp.data.remote

import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.request.LoginReq
import com.munaz.nutrisiapp.data.request.RegisReq
import com.munaz.nutrisiapp.data.response.*
import okhttp3.MultipartBody

internal interface DataResource {

    suspend fun register(regisReq: RegisReq): Resource<LoginResponse>
    suspend fun login(loginReq: LoginReq): Resource<LoginResponse>
    suspend fun listArikel():Resource<ArtikelResponse>
    suspend fun getFood(page : Int, limit : Int) :Resource<ResepResponse>
    suspend fun postImage(file : MultipartBody.Part) :Resource<ImageResponse>


}