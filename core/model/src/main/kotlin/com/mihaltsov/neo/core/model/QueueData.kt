package com.mihaltsov.neo.core.model

data class QueueData(val id: String, val persons: List<Person>) {

    data class Person(
        val id: String,
        val queueNumber: Int,
        val nickName: String,
        val isActive: Boolean,
        val isMine: Boolean,
    )
}