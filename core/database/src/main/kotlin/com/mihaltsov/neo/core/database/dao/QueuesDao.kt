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

package com.mihaltsov.neo.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.mihaltsov.neo.core.database.model.ExistQueuesDataEntity
import kotlinx.coroutines.flow.Flow

/*
 * DAO for [ExistQueuesDataEntity] access
 */
@Dao
interface QueuesDao {

    @Query(value = """SELECT * FROM existQueuesData""")
    fun getQueuesEntitiesFlow(): Flow<List<ExistQueuesDataEntity>>

    @Query(value = """SELECT * FROM existQueuesData""")
    fun getQueuesEntitiesList(): List<ExistQueuesDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrIgnoreQueues(entities: List<ExistQueuesDataEntity>): List<Long>

    @Upsert
    suspend fun upsertQueues(entities: List<ExistQueuesDataEntity>)

    @Query(value = """DELETE FROM existQueuesData WHERE id in (:ids)""")
    suspend fun deleteQueueByIds(ids: List<String>)

    @Query("""DELETE FROM existQueuesData""")
    suspend fun nukeTable()
}