package com.mihaltsov.neo.core.network.mapper

import com.mihaltsov.neo.core.model.QueueData
import com.mihaltsov.neo.core.model.QueueItemData
import com.mihaltsov.neo.core.model.UserData
import com.mihaltsov.neo.core.network.DTO.response.ExistQueuesDataResponse
import com.mihaltsov.neo.core.network.DTO.response.QueueDataResponse
import com.mihaltsov.neo.core.network.DTO.response.UserDataResponse

fun QueueDataResponse.mapToData(): QueueData {
    return QueueData(
        persons = persons.map {
            QueueData.Person(
                id = it.id,
                queueNumber = it.queueNumber.toInt(),
                nickName = it.nickName,
                isActive = it.isActive,
                isMine = false,
                queueId = id
            )
        })
}

fun UserDataResponse.mapToData(): UserData {
    return UserData(
        id = id,
        nickName = nickName,
        phone = phone,
        queues = queues
    )
}

fun ExistQueuesDataResponse.map(): List<QueueItemData> {
    return items.map { QueueItemData(it.id, it.title, it.description) }
}