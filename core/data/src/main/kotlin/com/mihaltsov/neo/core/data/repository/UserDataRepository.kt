package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>
}