package com.mihaltsov.neo.realtime

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.collection.ArraySortedMap
import com.google.firebase.database.database
import com.mihaltsov.neo.core.network.DTO.request.ApplyToQueueRequest
import com.mihaltsov.neo.core.network.DTO.response.EmptyResponse
import com.mihaltsov.neo.core.network.DTO.response.ExistQueuesDataResponse
import com.mihaltsov.neo.core.network.DTO.response.QueueDataResponse
import com.mihaltsov.neo.core.network.DTO.response.UserDataResponse
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.realtime.dto.QueueRealtime
import com.mihaltsov.neo.realtime.dto.UserDataRealtime
import java.lang.IllegalStateException
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class RealtimeDataSource @Inject constructor() : NeoNetworkDataSource {

    private val realTimeDatabase: DatabaseReference by lazy { Firebase.database.reference }

    override suspend fun userData(userId: String): UserDataResponse {
        return findUser(userId)?.let { userData ->
            UserDataResponse(
                id = userData.id.orEmpty(),
                nickName = userData.nickName.orEmpty(),
                phone = userData.phone.orEmpty(),
                registrationDate = userData.registrationDate.orEmpty(),
                queues = userData.queuesOfUser.orEmpty(),
            )
        } ?: UserDataResponse("", "", "", "", emptyList())
    }

    override suspend fun applyToQueue(request: ApplyToQueueRequest): EmptyResponse {
        val userData = findUser(request.personId) ?: throw IllegalArgumentException("User not found")
        val queueData = findQueue(request.queueId) ?: throw IllegalArgumentException("Queue not found")

        val childUpdates = hashMapOf<String, Any>(
            "/${TableKey.USERS.key}/${request.personId}/queuesOfUser" to userData.queuesOfUser.orEmpty() + request.queueId,
            "/${TableKey.QUEUES.key}/${request.queueId}/personsInQueueIds" to
                    queueData.personsInQueueIds.orEmpty() + request.personId,
        )
        realTimeDatabase.updateChildren(childUpdates)
        return EmptyResponse()
    }

    override suspend fun getQueueDetails(queueId: String): QueueDataResponse {
        val queueData = findQueue(queueId) ?: throw IllegalArgumentException("Queue not found")
        return QueueDataResponse(
            id = queueId,
            persons = queueData.personsInQueueIds.orEmpty().mapIndexed { userOrdinal, userId ->
                QueueDataResponse.PersonQueueData(userId, userOrdinal.toString(), findUser(userId)?.nickName.orEmpty(), true)
            }
        )
    }

    override suspend fun existingQueues(): ExistQueuesDataResponse {
        return suspendCoroutine { continuation ->
            realTimeDatabase.child(TableKey.QUEUES.key).get().addOnSuccessListener { snapshot ->
                continuation.resume(ExistQueuesDataResponse(
                    snapshot.getValue(object : GenericTypeIndicator<HashMap<String, QueueRealtime>>() {}).orEmpty().values.map {
                        ExistQueuesDataResponse.Queue(it.id.orEmpty(), it.title.orEmpty(), it.description.orEmpty())
                    }
                ))
            }.addOnFailureListener {
                continuation.resume(ExistQueuesDataResponse(emptyList()))
            }
        }
    }

    fun addQueue(title: String, description: String) {
        val newQueueEntry = QueueRealtime(UUID.randomUUID().toString(), title, description, emptyList())
        realTimeDatabase.child(TableKey.QUEUES.key).child(newQueueEntry.id.orEmpty()).setValue(newQueueEntry)
    }

    private fun <T> getDataFromSnapshot(snapshot: DataSnapshot): T? = snapshot.getValue(object : GenericTypeIndicator<T>() {})

    private suspend fun findUser(userId: String): UserDataRealtime? {
        return suspendCoroutine { continuation ->
            realTimeDatabase.child(TableKey.USERS.key).child(userId).get().addOnSuccessListener { snapshot ->
                continuation.resume(getDataFromSnapshot<UserDataRealtime>(snapshot))
            }.addOnFailureListener {
                throw it
            }
        }
    }

    private suspend fun findQueue(queueId: String): QueueRealtime? {
        return suspendCoroutine { continuation ->
            realTimeDatabase.child(TableKey.QUEUES.key).child(queueId).get().addOnSuccessListener { snapshot ->
                continuation.resume(getDataFromSnapshot<QueueRealtime>(snapshot))
            }.addOnFailureListener {
                throw it
            }
        }
    }
}