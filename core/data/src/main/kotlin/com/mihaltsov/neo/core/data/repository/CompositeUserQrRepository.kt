package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.model.UserQrModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CompositeUserQrRepository @Inject constructor(
    private val qrRepository: QrRepository,
    private val userDataRepository: UserDataRepository,
) {

    suspend fun getQrCode(userId: String): Flow<UserQrModel> {
        var queueNumber = ""
        userDataRepository.userData.collect() {
            queueNumber = it.queueNumber.toString()
        }
        return qrRepository.getUserQr(queueNumber)
    }
}