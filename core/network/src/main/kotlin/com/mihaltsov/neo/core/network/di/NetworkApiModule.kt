package com.mihaltsov.neo.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal class NetworkApiModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkApiService =
        retrofit.create(NetworkApiService::class.java)
}