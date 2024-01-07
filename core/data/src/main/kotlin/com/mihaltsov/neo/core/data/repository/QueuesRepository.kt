package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.data.util.Syncable
import com.mihaltsov.neo.core.model.QueueItemData
import kotlinx.coroutines.flow.Flow

interface QueuesRepository : Syncable {

    /**
     * Stream of [QueueItemData]
     */
    val existQueues: Flow<List<QueueItemData>>
}