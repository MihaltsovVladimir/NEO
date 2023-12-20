package com.mihaltsov.neo.core.data.repository.fake

import com.mihaltsov.neo.core.data.model.asEntity
import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.data.util.Synchronizer
import com.mihaltsov.neo.core.data.util.changeTestSync
import com.mihaltsov.neo.core.database.dao.PersonsQueueDao
import com.mihaltsov.neo.core.database.model.asExternalModel
import com.mihaltsov.neo.core.model.QueueData
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.mapper.mapToData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FakeOfflineFirstUserQueueRepository @Inject constructor(
    private val network: NeoNetworkDataSource,
    private val queueDao: PersonsQueueDao,
) : QueueDataRepository {

    override val queueData: Flow<QueueData> = queueDao.getPersonsEntitiesFlow()
        .map { QueueData(it.map { entity -> entity.asExternalModel() }) }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        return synchronizer.changeTestSync(
            networkData = network.queueData().data.mapToData().persons.map { it.asEntity() },
            dataBaseData = queueDao.getPersonsEntitiesList(),
            modelDeleter = queueDao::deletePersonsByIds,
            modelUpdater = queueDao::upsertPersons,
            modelAdd = queueDao::insertOrIgnorePersons
        )
    }
}