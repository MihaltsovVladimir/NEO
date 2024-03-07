package com.mihaltsov.neo.realtime.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDataRealtime(
    val id: String? = null,
    val nickName: String? = null,
    val phone: String? = null,
    val registrationDate: String? = null,
    val queuesOfUser: List<String>? = null
)