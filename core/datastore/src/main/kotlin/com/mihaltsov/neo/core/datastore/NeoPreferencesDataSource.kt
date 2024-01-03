/*
 * Copyright 2022 The Android Open Source Project
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

package com.mihaltsov.neo.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.mihaltsov.neo.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

class NeoPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {

    val userData: Flow<UserData>
        get() {
            return userPreferences.data.map {
                UserData(
                    id = it.userId,
                    nickName = it.nickName,
                    phone = it.phone,
                    queues = it.userQueuesMap,
                )
            }
        }

    /** id of device where app is run */
    val deviceId: Flow<String>
        get() {
            return userPreferences.data.map { it.deviceId }
        }

    suspend fun setUpDeviceId() {
        try {
            userPreferences.updateData {
                it.copy {
                    if (it.deviceId.isNullOrEmpty()) {
                        this.deviceId = UUID.randomUUID().toString()
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e("NeoPreferences", "Failed to update user preferences", ioException)
        }
    }

    suspend fun addNewQueue(queueId: String, number: String) {
        try {
            userPreferences.updateData {
                it.copy {
                    userQueues.put(queueId, number)
                }
            }
        } catch (ioException: IOException) {
            Log.e("NeoPreferences", "Failed to update user preferences", ioException)
        }
    }

    suspend fun removeQueue(queueId: String) {
        try {
            userPreferences.updateData {
                it.copy {
                    userQueues.remove(queueId)
                }
            }
        } catch (ioException: IOException) {
            Log.e("NeoPreferences", "Failed to update user preferences", ioException)
        }
    }

    suspend fun setUpUserData(userData: UserData) {
        try {
            userPreferences.updateData {
                it.copy {
                    this.userId = userData.id
                    this.nickName = userData.nickName
                    this.phone = userData.phone
                    this.userQueues.clear()
                    userData.queues.forEach { it ->
                        userQueues.put(it.key, it.value)
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e("NeoPreferences", "Failed to update user preferences", ioException)
        }
    }
}