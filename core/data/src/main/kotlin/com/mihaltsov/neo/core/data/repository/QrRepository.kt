package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.model.UserQrModel
import kotlinx.coroutines.flow.Flow

interface QrRepository {

    /**
     * Stream of [UserQrModel]
     */
    suspend fun getUserQr(userId: String): Flow<UserQrModel>
}