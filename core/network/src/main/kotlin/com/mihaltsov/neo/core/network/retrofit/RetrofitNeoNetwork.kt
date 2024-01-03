/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mihaltsov.neo.core.network.retrofit

import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.di.NetworkApiService
import com.mihaltsov.neo.core.network.model.QueueDataResponse
import com.mihaltsov.neo.core.network.model.UserDataResponse
import kotlinx.serialization.Serializable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * [Retrofit] backed [NeoNetworkDataSource]
 */
class RetrofitNeoNetwork @Inject constructor(
    private val apiService: NetworkApiService,
) : NeoNetworkDataSource {

    override suspend fun userData(): UserDataResponse {
        return apiService.getUserData()
    }

    override suspend fun queueData(): QueueDataResponse {
        return apiService.getQueue()
    }
}
