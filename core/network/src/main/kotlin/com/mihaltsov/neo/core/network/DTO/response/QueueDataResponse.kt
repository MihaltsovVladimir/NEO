package com.mihaltsov.neo.core.network.DTO.response

import kotlinx.serialization.Serializable

/**
 * Network representation of [QueueDataResponse]
 */
@Serializable
data class QueueDataResponse(val id: String, val persons: List<PersonQueueData>) {

    @Serializable
    data class PersonQueueData(
        val id: String,
        val queueNumber: String,
        val nickName: String,
        val isActive: Boolean,
    )
}