package com.mihaltsov.neo.core.data.dto

data class UserDataDto(
    val nickName: String,
    val phone: String,
    val registrationDate: String,
    val queueNumber: Int
)