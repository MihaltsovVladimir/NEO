package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.data.util.Synchronizer
import com.mihaltsov.neo.core.data.util.changeExistQueuesSync
import com.mihaltsov.neo.core.database.dao.QueuesDao
import com.mihaltsov.neo.core.database.model.ExistQueuesDataEntity
import com.mihaltsov.neo.core.database.model.asExternalModel
import com.mihaltsov.neo.core.model.QueueItemData
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.mapper.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstQueuesRepository @Inject constructor(
    private val network: NeoNetworkDataSource,
    private val queuesDao: QueuesDao,
) : QueuesRepository {

    override val existQueues: Flow<List<QueueItemData>> =
        queuesDao.getQueuesEntitiesFlow().map { entity ->
            entity.map { it.asExternalModel() }
        }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        return synchronizer.changeExistQueuesSync(
            networkData = network.existQueue().map().map { ExistQueuesDataEntity(it.id, it.title, it.description) },
            dataBaseData = queuesDao.getQueuesEntitiesList(),
            modelUpdater = queuesDao::upsertQueues,
            modelDeleter = queuesDao::deleteQueueByIds,
            modelAdd = queuesDao::insertOrIgnoreQueues
        )
    }
}