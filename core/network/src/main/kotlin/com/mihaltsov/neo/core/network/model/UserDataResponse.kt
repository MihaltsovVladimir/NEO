package com.mihaltsov.neo.core.network.model

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
    val queues: List<Queues>,
) {

    @Serializable
    data class Queues(
        val id: String,
        val number: String,
    )
}