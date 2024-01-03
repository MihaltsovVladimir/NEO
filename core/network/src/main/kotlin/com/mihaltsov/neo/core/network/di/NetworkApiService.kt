package com.mihaltsov.neo.core.network.di

import com.mihaltsov.neo.core.network.model.QueueDataResponse
import com.mihaltsov.neo.core.network.model.UserDataResponse
import retrofit2.http.GET

/**
 * Retrofit API declaration for NEO Network API
 */
interface NetworkApiService {

    @GET("user")
    suspend fun getUserData(): UserDataResponse

    @GET("queue")
    suspend fun getQueue(): QueueDataResponse
}