package com.mihaltsov.neo.core.data.repository.fake

import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.model.QueueData
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.mapper.mapToData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FakeOfflineFirstUserQueueRepository @Inject constructor(
    networkData: NeoNetworkDataSource
) : QueueDataRepository {

    override val queueData: Flow<QueueData> = flow {
        emit(networkData.queueData().mapToData())
    }
}