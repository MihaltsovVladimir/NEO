package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.data.model.asEntity
import com.mihaltsov.neo.core.data.util.Synchronizer
import com.mihaltsov.neo.core.data.util.changeQueueSync
import com.mihaltsov.neo.core.database.dao.PersonsQueueDao
import com.mihaltsov.neo.core.database.model.asExternalModel
import com.mihaltsov.neo.core.model.QueueData
import com.mihaltsov.neo.core.model.UserData
import com.mihaltsov.neo.core.network.DTO.request.ApplyToQueueRequest
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.mapper.mapToData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class OfflineFirstPersonQueueRepository @Inject constructor(
    private val network: NeoNetworkDataSource,
    private val database: PersonsQueueDao,
) : QueueDataRepository {

    private val queueIdFlow = MutableStateFlow("")

    override val queueData: Flow<QueueData>
        get() = combine(
            database.getPersonsEntitiesFlow(),
            queueIdFlow
        ) { database, queueId ->
            val filteredByQueueId = database.filter { it.queueId == queueId }
            QueueData(persons = filteredByQueueId.map { entity -> entity.asExternalModel() })
        }

    override suspend fun getQueueDetails(queueId: String) {
        queueIdFlow.emit(queueId)
        val networkData = network.getQueueDetails(queueId)
        database.insertOrIgnorePersons(networkData.mapToData().asEntity())
    }

    override suspend fun applyToQueue(queueId: String, userData: UserData) {
        network.applyToQueue(
            ApplyToQueueRequest(
                queueId = queueId,
                id = userData.id,
                nickName = userData.nickName,
                queueNumber = database.getCount(queueId).toString(),
            )
        )
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