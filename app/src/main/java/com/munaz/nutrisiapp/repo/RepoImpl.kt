package com.munaz.nutrisiapp.repo

import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.local.dataStoreApp
import com.munaz.nutrisiapp.data.remote.RemoteData
import com.munaz.nutrisiapp.data.request.LoginReq
import com.munaz.nutrisiapp.data.request.RegisReq
import com.munaz.nutrisiapp.data.response.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RepoImpl @Inject constructor(
    private val remoteRepository: RemoteData,
    private val localRepository: dataStoreApp,
    private val ioDispatcher: CoroutineContext
) : Repo {
    override suspend fun doRegister(registReq: RegisReq): Flow<Resource<LoginResponse>> {
        return flow {
            emit(remoteRepository.register(registReq))
        }.flowOn(ioDispatcher)
    }

    override suspend fun doLogin(loginReq: LoginReq): Flow<Resource<LoginResponse>> {
        return flow {
            emit(remoteRepository.login(loginReq))
        }.flowOn(ioDispatcher)
    }

    override suspend fun saveToken(tokenmodel: String): Flow<Resource<Boolean>> {
        return flow {
            emit(localRepository.saveToken(tokenmodel))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getToken(): Flow<Flow<String?>> {
        return flow {
            emit(localRepository.getTOKEN())
        }.flowOn(ioDispatcher)
    }

    override suspend fun doGetListArtikel(): Flow<Resource<ArtikelResponse>>{
        return flow {
            emit(remoteRepository.listArikel())
        }.flowOn(ioDispatcher)
    }

    override suspend fun doGetFoodResep(page: Int, limit: Int): Flow<Resource<ResepResponse>> {
        return flow {
            emit(remoteRepository.getFood(page,limit))
        }.flowOn(ioDispatcher)
    }

    override suspend fun doPostImage(file: MultipartBody.Part):Flow<Resource<ImageResponse>> {
        return flow {
            emit(remoteRepository.postImage(file))
        }.flowOn(ioDispatcher)
    }
}