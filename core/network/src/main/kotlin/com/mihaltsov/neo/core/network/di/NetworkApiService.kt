package com.mihaltsov.neo.core.network.di

import com.mihaltsov.neo.core.network.DTO.request.ApplyToQueueRequest
import com.mihaltsov.neo.core.network.DTO.response.EmptyResponse
import com.mihaltsov.neo.core.network.DTO.response.ExistQueuesDataResponse
import com.mihaltsov.neo.core.network.DTO.response.QueueDataResponse
import com.mihaltsov.neo.core.network.DTO.response.UserDataResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Retrofit API declaration for NEO Network API
 */
interface NetworkApiService {

    @GET("user")
    suspend fun getUserData(): UserDataResponse

    @GET("queues")
    suspend fun getQueue(): QueueDataResponse

    @GET("queues")
    suspend fun getQueueDetails(@Query("id") queueId: String): List<QueueDataResponse>

    @POST("queues")
    suspend fun postApply(@Body request: ApplyToQueueRequest): EmptyResponse

    @GET("existqueues")
    suspend fun getQueues(): ExistQueuesDataResponse
}