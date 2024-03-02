package com.mihaltsov.neo.core.network.DTO.request

import kotlinx.serialization.Serializable

@Serializable
data class ApplyToQueueRequest(
    val queueId: String,
    val personId: String,
)