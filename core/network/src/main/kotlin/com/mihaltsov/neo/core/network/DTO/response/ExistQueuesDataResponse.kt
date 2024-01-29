package com.mihaltsov.neo.core.network.DTO.response

import kotlinx.serialization.Serializable

@Serializable
data class ExistQueuesDataResponse(val items: List<Queue>) {

    @Serializable
    data class Queue(val id: String, val title: String, val description: String)
}