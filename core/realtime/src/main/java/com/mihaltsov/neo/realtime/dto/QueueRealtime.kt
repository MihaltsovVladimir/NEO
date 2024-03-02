package com.mihaltsov.neo.realtime.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class QueueRealtime(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val personsInQueueIds: List<String>? = null
)