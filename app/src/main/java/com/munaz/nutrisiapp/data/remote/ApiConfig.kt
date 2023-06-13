package com.munaz.nutrisiapp.data.remote

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.viewbinding.BuildConfig
import com.munaz.nutrisiapp.data.local.Tokenmodel
import com.munaz.nutrisiapp.data.local.dataStoreApp
import com.munaz.nutrisiapp.utils.BASE_URL
import com.munaz.nutrisiapp.utils.BASE_URL2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


private const val timeoutRead = 30   //In seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 30   //In seconds

@Singleton
class ApiConfig @Inject constructor(dataStoreApp: dataStoreApp) {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit
    private val retrofitImg: Retrofit

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        val token = runBlocking { dataStoreApp.getTOKEN().first() }
        if (token == null) {
            val request = original
                .newBuilder()
                .header(contentType, contentTypeValue)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        } else {
            val request = original
                .newBuilder()
                .header(contentType, contentTypeValue)
                .header("Authorization", "Bearer $token")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
    }

    init {
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        retrofitImg = Retrofit.Builder().baseUrl(BASE_URL2).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
    fun <S> createService2(serviceClass: Class<S>): S {
        return retrofitImg.create(serviceClass)
    }

}