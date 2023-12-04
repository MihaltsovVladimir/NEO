package com.mihaltsov.neo.core.network.di

import com.mihaltsov.neo.core.network.model.QueueDataResponse
import com.mihaltsov.neo.core.network.model.UserDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API declaration for NEO Network API
 */
interface NetworkApiService {

    @GET("card-transfer/initialization")
    suspend fun getUserData(): UserDataResponse

    @GET("card-transfer/encrypted-key")
    suspend fun getQueue(@Query("userId") userId: String): QueueDataResponse
}