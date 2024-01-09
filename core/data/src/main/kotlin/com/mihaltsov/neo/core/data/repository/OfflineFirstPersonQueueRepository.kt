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
    private val database: PersonsQueueDao,
) : QueueDataRepository {

    override val queueData: Flow<QueueData> = database.getPersonsEntitiesFlow().map {
        QueueData(it.map { entity -> entity.asExternalModel() })
    }

    override suspend fun getQueueDetails(queueId: String) {
        if (database.getPersonsEntitiesListByQueueId(queueId).isEmpty()) {
            val networkData = network.getQueueDetails(queueId)
            database.insertOrIgnorePersons(networkData.mapToData().asEntity())
        }
    }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        return synchronizer.changeQueueSync(
            networkData = network.queueData().mapToData().asEntity(),
            dataBaseData = database.getPersonsEntitiesList(),
            modelDeleter = database::deletePersonsByIds,
            modelUpdater = database::upsertPersons,
            modelAdd = database::insertOrIgnorePersons
        )
    }
}