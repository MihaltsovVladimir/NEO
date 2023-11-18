package com.mihaltsov.core.model

data class QueueData(val persons: List<PersonQueueData>) {

    data class PersonQueueData(val queueNumber: Int, val nickName: String, val oldPosition: Int, val newPosition: Int)
}