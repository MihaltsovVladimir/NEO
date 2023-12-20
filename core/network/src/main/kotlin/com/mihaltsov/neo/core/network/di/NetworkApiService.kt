package com.mihaltsov.neo.core.network.di

import com.mihaltsov.neo.core.network.model.QueueDataResponse
import com.mihaltsov.neo.core.network.model.UserDataResponse
import com.mihaltsov.neo.core.network.retrofit.NetworkResponse
import retrofit2.http.GET

/**
 * Retrofit API declaration for NEO Network API
 */
interface NetworkApiService {

    @GET("profile/getUserInfo")
    suspend fun getUserData(): UserDataResponse

    @GET("Profile/GetUserInfo")
    suspend fun getQueue(): NetworkResponse<QueueDataResponse>
}