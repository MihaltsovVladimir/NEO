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

package com.mihaltsov.neo.core.network.fake

import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.model.QueueDataResponse
import com.mihaltsov.neo.core.network.retrofit.NetworkResponse
import javax.inject.Inject

/**
 * [NeoNetworkDataSource] implementation that provides static queue resources to aid development
 */
class FakeNeoNetworkDataSource @Inject constructor() : NeoNetworkDataSource {

    override suspend fun queueData(): NetworkResponse<QueueDataResponse> {
        return fakeQueue()
    }

    private fun fakeQueue(): NetworkResponse<QueueDataResponse> {
        return NetworkResponse(
            QueueDataResponse(
                listOf(
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6000",
                        nickName = "Mihaltsov 6000",
                        queueNumber = "6000",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6500",
                        nickName = "Mihaltsov 6500",
                        queueNumber = "6500",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6700",
                        nickName = "Mihaltsov 6700",
                        queueNumber = "6700",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6800",
                        nickName = "Mihaltsov 6800",
                        queueNumber = "6800",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6900",
                        nickName = "Mihaltsov 6900",
                        queueNumber = "6900",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6910",
                        nickName = "Mihaltsov 6910",
                        queueNumber = "6910",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6911",
                        nickName = "Mihaltsov 6911",
                        queueNumber = "6911",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6912",
                        nickName = "Mihaltsov 6912",
                        queueNumber = "6912",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6913",
                        nickName = "Mihaltsov 6913",
                        queueNumber = "6913",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6914",
                        nickName = "Mihaltsov 6914",
                        queueNumber = "6914",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6915",
                        nickName = "Mihaltsov 6915",
                        queueNumber = "6915",
                        isActive = true,
                    ),
                    QueueDataResponse.PersonQueueData(
                        id = "Mihaltsov 6916",
                        nickName = "Mihaltsov 6916",
                        queueNumber = "6916",
                        isActive = true,
                    ),
                )
            )
        )
    }
}