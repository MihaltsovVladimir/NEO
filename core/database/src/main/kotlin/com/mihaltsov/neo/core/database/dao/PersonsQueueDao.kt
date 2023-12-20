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
import com.mihaltsov.neo.core.database.model.PersonQueueDataEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [PersonQueueDataEntity] access
 */
@Dao
interface PersonsQueueDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertPersons(persons: List<PersonQueueDataEntity>)

    @Query(value = """SELECT * FROM personQueueData""")
    fun getPersonsEntitiesFlow(): Flow<List<PersonQueueDataEntity>>

    @Query(value = """SELECT * FROM personQueueData""")
    fun getPersonsEntitiesList(): List<PersonQueueDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrIgnorePersons(entities: List<PersonQueueDataEntity>): List<Long>

    @Upsert
    suspend fun upsertPersons(entities: List<PersonQueueDataEntity>)

    @Query(value = """DELETE FROM personQueueData WHERE id in (:ids)""")
    suspend fun deletePersonsByIds(ids: List<String>)

    @Query("""DELETE FROM personQueueData""")
    suspend fun nukeTable()
}
