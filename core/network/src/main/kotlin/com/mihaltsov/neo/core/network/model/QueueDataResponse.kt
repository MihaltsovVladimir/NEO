package com.mihaltsov.neo.core.network.model

import kotlinx.serialization.Serializable

/**
 * Network representation of [QueueDataResponse]
 */
@Serializable
data class QueueDataResponse(val persons: List<PersonQueueData>) {

    @Serializable
    data class PersonQueueData(
        val id: String,
        val queueNumber: Int,
        val nickName: String,
        val oldPosition: Int,
        val newPosition: Int
    )
}