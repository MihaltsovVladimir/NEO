package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.data.util.Syncable
import com.mihaltsov.neo.core.model.QueueData
import kotlinx.coroutines.flow.Flow

interface QueueDataRepository : Syncable {

    /**
     * Stream of [QueueData]
     */
    val queueData: Flow<QueueData>
}