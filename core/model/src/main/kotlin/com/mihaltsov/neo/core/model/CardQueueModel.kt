package com.mihaltsov.neo.core.model

data class CardQueueModel (
    val queueNumber: Int, val nickName: String, val oldPosition: Int, val newPosition: Int
)