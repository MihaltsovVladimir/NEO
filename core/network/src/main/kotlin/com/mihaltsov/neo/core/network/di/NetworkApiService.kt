package com.mihaltsov.neo.core.network.di

import com.mihaltsov.neo.core.network.model.ExistQueuesDataResponse
import com.mihaltsov.neo.core.network.model.QueueDataResponse
import com.mihaltsov.neo.core.network.model.UserDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API declaration for NEO Network API
 */
interface NetworkApiService {

    @GET("user")
    suspend fun getUserData(): UserDataResponse

    @GET("queue")
    suspend fun getQueue(): QueueDataResponse

    @GET("queueDetail/")
    suspend fun getQueueDetails(@Query("id") queueId: String): List<QueueDataResponse>

    @GET("existqueues")
    suspend fun getQueues(): ExistQueuesDataResponse
}