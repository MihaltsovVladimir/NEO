package com.mihaltsov.neo.core.data.repository.fake

import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.data.util.Synchronizer
import com.mihaltsov.neo.core.model.QueueData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeOfflineFirstQueueRepository @Inject constructor() : QueueDataRepository {

    override val queueData: Flow<QueueData> = flow { QueueData(listOf()) }

    override suspend fun getQueueDetails(queueId: String) {}

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        return true
    }
}