package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.data.model.asEntity
import com.mihaltsov.neo.core.data.util.Synchronizer
import com.mihaltsov.neo.core.data.util.changeQueueSync
import com.mihaltsov.neo.core.database.dao.PersonsQueueDao
import com.mihaltsov.neo.core.database.model.asExternalModel
import com.mihaltsov.neo.core.model.QueueData
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.mapper.mapToData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstPersonQueueRepository @Inject constructor(
    private val network: NeoNetworkDataSource,
    private val queueDao: PersonsQueueDao,
) : QueueDataRepository {

    override val queueData: Flow<QueueData> = queueDao.getPersonsEntitiesFlow().map {
        QueueData(
            id = "it[0].queueId",
            persons = it.map { entity -> entity.asExternalModel() }
        )
    }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        return synchronizer.changeQueueSync(
            networkData = network.queueData().mapToData().asEntity(),
            dataBaseData = queueDao.getPersonsEntitiesList(),
            modelDeleter = queueDao::deletePersonsByIds,
            modelUpdater = queueDao::upsertPersons,
            modelAdd = queueDao::insertOrIgnorePersons
        )
    }
}