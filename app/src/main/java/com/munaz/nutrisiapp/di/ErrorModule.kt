package com.munaz.nutrisiapp.di

import com.munaz.nutrisiapp.error.mapper.ErrorMapper
import com.munaz.nutrisiapp.error.mapper.Errorsrc
import com.munaz.nutrisiapp.usecase.error.ErrorCase
import com.munaz.nutrisiapp.usecase.error.ErrorManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): Errorsrc
}