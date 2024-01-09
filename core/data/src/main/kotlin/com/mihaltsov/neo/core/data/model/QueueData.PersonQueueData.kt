package com.mihaltsov.neo.core.data.model

import com.mihaltsov.neo.core.database.model.PersonQueueDataEntity
import com.mihaltsov.neo.core.model.QueueData

fun QueueData.asEntity(): List<PersonQueueDataEntity> {
    return persons.map {
        PersonQueueDataEntity(
            id = it.id,
            queueNumber = it.queueNumber,
            nickName = it.nickName,
            isActive = it.isActive,
            queueId = it.queueId
        )
    }
}