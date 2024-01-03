package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.data.util.Syncable
import com.mihaltsov.neo.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository : Syncable {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>
}