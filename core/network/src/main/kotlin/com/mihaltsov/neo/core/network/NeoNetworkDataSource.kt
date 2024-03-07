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

package com.mihaltsov.neo.core.network

import com.mihaltsov.neo.core.network.DTO.request.ApplyToQueueRequest
import com.mihaltsov.neo.core.network.DTO.response.EmptyResponse
import com.mihaltsov.neo.core.network.DTO.response.ExistQueuesDataResponse
import com.mihaltsov.neo.core.network.DTO.response.QueueDataResponse
import com.mihaltsov.neo.core.network.DTO.response.UserDataResponse

/**
 * Interface representing network calls to the NEO backend
 */
interface NeoNetworkDataSource {

    suspend fun userData(userId: String): UserDataResponse

    suspend fun getQueueDetails(queueId: String): QueueDataResponse

    suspend fun existingQueues(): ExistQueuesDataResponse

    suspend fun applyToQueue(request: ApplyToQueueRequest): EmptyResponse
}
