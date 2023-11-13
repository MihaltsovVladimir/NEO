package com.mihaltsov.core.data.repository

import com.mihaltsov.core.data.UserDataDto
import com.mihaltsov.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserDataDto]
     */
    val userData: Flow<UserData>
}