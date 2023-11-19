package by.mihaltsov.core.data.repository

import by.mihaltsov.core.model.QueueData
import kotlinx.coroutines.flow.Flow

interface QueueDataRepository {

    /**
     * Stream of [QueueData]
     */
    val queueData: Flow<QueueData>
}