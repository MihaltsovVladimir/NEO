package com.mihaltsov.neo.core.data.model

import com.mihaltsov.neo.core.database.model.PersonQueueDataEntity
import com.mihaltsov.neo.core.model.QueueData

fun QueueData.PersonQueueData.asEntity(): PersonQueueDataEntity {
    return PersonQueueDataEntity(
        id = id,
        queueNumber = queueNumber,
        nickName = nickName,
        isActive = isActive,
    )
}