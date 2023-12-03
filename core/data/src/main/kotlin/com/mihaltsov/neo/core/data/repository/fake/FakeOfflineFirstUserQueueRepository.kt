package com.mihaltsov.neo.core.data.repository.fake

import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.model.QueueData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

private fun fakeQueue(): QueueData {
    val list = mutableListOf<QueueData.PersonQueueData>()
    repeat(100) {
        list.add(
            QueueData.PersonQueueData(
                nickName = "Mihaltsov",
                queueNumber = it,
                oldPosition = 0,
                newPosition = 2
            )
        )
    }
    return QueueData(list)
}

class FakeOfflineFirstUserQueueRepository @Inject constructor() : QueueDataRepository {

    override val queueData: Flow<QueueData> = flowOf(fakeQueue())
}