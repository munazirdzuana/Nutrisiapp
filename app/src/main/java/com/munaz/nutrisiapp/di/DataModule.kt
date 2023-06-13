package com.munaz.nutrisiapp.di

import com.munaz.nutrisiapp.repo.Repo
import com.munaz.nutrisiapp.repo.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: RepoImpl): Repo
}