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

package com.mihaltsov.neo.core.data.util

import android.util.Log
import com.mihaltsov.neo.core.database.model.PersonQueueDataEntity
import com.mihaltsov.neo.core.model.UserData
import kotlin.coroutines.cancellation.CancellationException

/**
 * Interface marker for a class that manages synchronization between local data and a remote
 * source for a [Syncable].
 */
interface Synchronizer {

    /**
     * Syntactic sugar to call [Syncable.syncWith] while omitting the synchronizer argument
     */
    suspend fun Syncable.sync() = this@sync.syncWith(this@Synchronizer)
}

/**
 * Interface marker for a class that is synchronized with a remote source. Syncing must not be
 * performed concurrently and it is the [Synchronizer]'s responsibility to ensure this.
 */
interface Syncable {

    /**
     * Synchronizes the local database backing the repository with the network.
     * Returns if the sync was successful or not.
     */
    suspend fun syncWith(synchronizer: Synchronizer): Boolean
}

/**
 * Attempts [block], returning a successful [Result] if it succeeds, otherwise a [Result.Failure]
 * taking care not to break structured concurrency
 */
private suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Log.i("suspendRunCatching", "Failed to evaluate a suspendRunCatchingBlock. Returning failure Result", exception)
    Result.failure(exception)
}

suspend fun Synchronizer.changeQueueSync(
    networkData: List<PersonQueueDataEntity>,
    dataBaseData: List<PersonQueueDataEntity>,
    modelDeleter: suspend (List<String>) -> Unit,
    modelUpdater: suspend (List<PersonQueueDataEntity>) -> Unit,
    modelAdd: suspend (List<PersonQueueDataEntity>) -> Unit,
) = suspendRunCatching {
    if (networkData.isEmpty()) {
        return@suspendRunCatching true
    }

    val addNewItems: MutableList<PersonQueueDataEntity> = mutableListOf()
    val updateItems: MutableList<PersonQueueDataEntity> = mutableListOf()
    val deleteItems: MutableList<PersonQueueDataEntity> = mutableListOf()
    networkData.forEach {
        if (!dataBaseData.contains(it)) {
            addNewItems.add(it)
        } else {
            updateItems.add(it)
        }
    }
    dataBaseData.forEach {
        if (!networkData.contains(it)) {
            deleteItems.add(it)
        }
    }
//    val (deleted, updated) = updateItems.partition(PersonQueueDataEntity::isActive)
    modelDeleter(deleteItems.map { it.id })
    modelUpdater(updateItems)
    modelAdd(addNewItems)
}.isSuccess

suspend fun Synchronizer.changeUserDataSync(
    networkData: UserData,
    modelUpdater: suspend (UserData) -> Unit,
) = suspendRunCatching {
    modelUpdater(networkData)
}.isSuccess