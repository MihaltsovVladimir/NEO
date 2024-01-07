package com.mihaltsov.neo.core.network.mapper

import com.mihaltsov.neo.core.model.QueueData
import com.mihaltsov.neo.core.model.QueueItemData
import com.mihaltsov.neo.core.model.UserData
import com.mihaltsov.neo.core.network.model.ExistQueuesDataResponse
import com.mihaltsov.neo.core.network.model.QueueDataResponse
import com.mihaltsov.neo.core.network.model.UserDataResponse

fun QueueDataResponse.mapToData(): QueueData {
    return QueueData(
        id = id,
        persons = persons.map {
            QueueData.Person(
                id = it.id,
                queueNumber = it.queueNumber.toInt(),
                nickName = it.nickName,
                isActive = it.isActive,
                isMine = false,
            )
        })
}

fun UserDataResponse.mapToData(): UserData {
    val map = mutableMapOf<String, String>()
    queues.forEach {
        map[it.id] = it.number
    }
    return UserData(
        id = id,
        nickName = nickName,
        phone = phone,
        queues = map
    )
}

fun ExistQueuesDataResponse.map(): List<QueueItemData> {
    return items.map { QueueItemData(it.id, it.title, it.description) }
}