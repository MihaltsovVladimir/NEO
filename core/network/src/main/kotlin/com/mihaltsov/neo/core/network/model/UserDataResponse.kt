package com.mihaltsov.neo.core.network.model

import kotlinx.serialization.Serializable

/**
 * Network representation of [UserDataResponse]
 */
@Serializable
data class UserDataResponse(
    val nickName: String,
    val phone: String,
    val registrationDate: String,
    val queueNumber: Int
)