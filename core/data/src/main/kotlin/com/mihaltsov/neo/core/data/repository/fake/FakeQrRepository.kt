package com.mihaltsov.neo.core.data.repository.fake

import com.mihaltsov.neo.core.common.network.Dispatcher
import com.mihaltsov.neo.core.common.network.NeoDispatchers.IO
import com.mihaltsov.neo.core.data.repository.QrRepository
import com.mihaltsov.neo.core.model.UserQrModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FakeQrRepository @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : QrRepository {

    override suspend fun getUserQr(userId: String): Flow<UserQrModel> {
        return flow {
            emit(UserQrModel(qrUrl = "https://api.qrserver.com/v1/create-qr-code/?data=${userId}&amp;size=10x10"))
        }.flowOn(ioDispatcher)
    }
}