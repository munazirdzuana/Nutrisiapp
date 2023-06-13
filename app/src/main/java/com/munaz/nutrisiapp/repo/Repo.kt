package com.munaz.nutrisiapp.repo

import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.request.LoginReq
import com.munaz.nutrisiapp.data.request.RegisReq
import com.munaz.nutrisiapp.data.response.*
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface Repo {
    suspend fun doRegister(registReq: RegisReq): Flow<Resource<LoginResponse>>
    suspend fun doLogin(loginReq: LoginReq): Flow<Resource<LoginResponse>>
    suspend fun saveToken(tokenmodel: String):Flow<Resource<Boolean>>
    suspend fun getToken():Flow<Flow<String?>>
    suspend fun doGetListArtikel():Flow<Resource<ArtikelResponse>>
    suspend fun doGetFoodResep(page : Int, limit : Int):Flow<Resource<ResepResponse>>
    suspend fun doPostImage(file:MultipartBody.Part):Flow<Resource<ImageResponse>>
}