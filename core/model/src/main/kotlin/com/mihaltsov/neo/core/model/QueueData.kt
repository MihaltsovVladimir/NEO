package com.mihaltsov.neo.core.model

data class QueueData(val persons: List<PersonQueueData>) {

    data class PersonQueueData(
        val id: String,
        val queueNumber: Int,
        val nickName: String,
        val isActive: Boolean
    )
}