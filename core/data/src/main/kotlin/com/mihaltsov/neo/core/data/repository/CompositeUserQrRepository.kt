package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.model.UserQrModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CompositeUserQrRepository @Inject constructor(
    private val qrRepository: QrRepository,
    private val userDataRepository: UserDataRepository,
) {

    operator fun invoke(newTestData: String): Flow<UserQrModel> = runBlocking {
        qrRepository.getUserQr(newTestData)
    }

//        userDataRepository.userData
//            .flatMapLatest {
//                qrRepository.getUserQr(it.queueNumber.toString())
//            }
}