package com.munaz.nutrisiapp.data.remote

import com.munaz.nutrisiapp.data.request.LoginReq
import com.munaz.nutrisiapp.data.request.RegisReq
import com.munaz.nutrisiapp.data.request.RekomendasiReq
import com.munaz.nutrisiapp.data.response.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @POST("register")
    @Headers("No-Authentication: true")
    suspend fun register(
        @Body regisReq: RegisReq
    ): Response<LoginResponse>

    @POST("login")
    @Headers("No-Authentication: true")
    suspend fun login(
        @Body loginReq: LoginReq
    ): Response<LoginResponse>

    //    @GET("user")
//    suspend fun user(
//        @Body loginReq: LoginReq
//    ): Response<LoginResponse>
//
    @GET("articles")
    suspend fun artikelList(
    ): Response<ArtikelResponse>

    @GET("foods-get")
    suspend fun resepList(
        @Query("page") page: Int, @Query("limit") limit: Int
    ): Response<ResepResponse>

    @Multipart
    @POST("upload-image-food")
    suspend fun image(
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>

    @POST("foods-recommendation")
    suspend fun getrecomend(
        @Body req: RekomendasiReq
    ): Response<RecomendasiResponseX>
}