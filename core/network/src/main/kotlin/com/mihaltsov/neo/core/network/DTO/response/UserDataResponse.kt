package com.mihaltsov.neo.core.network.DTO.response

import kotlinx.serialization.Serializable

/**
 * Network representation of [UserDataResponse]
 */
@Serializable
data class UserDataResponse(
    val id: String,
    val nickName: String,
    val phone: String,
    val registrationDate: String,
    val queues: List<String>
)