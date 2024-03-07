package com.mihaltsov.neo.core.model

data class UserData(
    val id: String,
    val nickName: String,
    val phone: String,
    val queues: List<String>,
)