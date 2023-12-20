package com.mihaltsov.neo.core.network.mapper

import com.mihaltsov.neo.core.model.QueueData
import com.mihaltsov.neo.core.network.model.QueueDataResponse

fun QueueDataResponse.mapToData(): QueueData {
    return QueueData(persons.map {
        QueueData.PersonQueueData(
            id = it.id,
            queueNumber = it.queueNumber.toInt(),
            nickName = it.nickName,
            isActive = it.isActive,
        )
    })
}