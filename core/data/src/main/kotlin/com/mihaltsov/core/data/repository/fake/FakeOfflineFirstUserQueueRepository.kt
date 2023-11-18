package com.mihaltsov.core.data.repository.fake

import com.mihaltsov.core.data.repository.QueueDataRepository
import com.mihaltsov.core.model.QueueData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeOfflineFirstUserQueueRepository() : QueueDataRepository {

    private val model1 = QueueData.PersonQueueData(
        nickName = "Mihaltsov1",
        queueNumber = 0,
        oldPosition = 0,
        newPosition = 2
    )
    private val model2 = QueueData.PersonQueueData(
        nickName = "Mihaltsov2",
        queueNumber = 1,
        oldPosition = 0,
        newPosition = 2
    )
    override val queueData: Flow<QueueData> = flowOf(QueueData(listOf(model1, model2)))
}