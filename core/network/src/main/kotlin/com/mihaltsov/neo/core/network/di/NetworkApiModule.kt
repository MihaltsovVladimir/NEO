package com.mihaltsov.neo.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkApiModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkApiService =
        retrofit.create(NetworkApiService::class.java)
}