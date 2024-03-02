package com.mihaltsov.neo.realtime

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.database
import com.mihaltsov.neo.core.network.DTO.request.ApplyToQueueRequest
import com.mihaltsov.neo.core.network.DTO.response.EmptyResponse
import com.mihaltsov.neo.core.network.DTO.response.ExistQueuesDataResponse
import com.mihaltsov.neo.core.network.DTO.response.QueueDataResponse
import com.mihaltsov.neo.core.network.DTO.response.UserDataResponse
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.realtime.dto.QueueRealtime
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val QUEUES = "queues"

class RealtimeDataSource @Inject constructor() : NeoNetworkDataSource {

    private val realTimeDatabase: DatabaseReference by lazy { Firebase.database.reference }

    fun addQueue(title: String, description: String) {
        getQueuesTask().addOnSuccessListener {
            val newQueueEntry = QueueRealtime(UUID.randomUUID().toString(), title, description, emptyList())
            realTimeDatabase.child(QUEUES).setValue(getQueuesFromSnapshot(it) + newQueueEntry)
        }
    }

    private fun getQueuesTask(): Task<DataSnapshot> = realTimeDatabase.child(QUEUES).get()

    private fun getQueuesFromSnapshot(snapshot: DataSnapshot): List<QueueRealtime> =
        snapshot.getValue(object : GenericTypeIndicator<List<QueueRealtime>>() {}).orEmpty()

    override suspend fun userData(): UserDataResponse {
        TODO("Not yet implemented")
    }

    override suspend fun queueData(): QueueDataResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getQueueDetails(queueId: String): QueueDataResponse {
        TODO("Not yet implemented")
    }

    override suspend fun existQueue(): ExistQueuesDataResponse {
        return suspendCoroutine { continuation ->
            getQueuesTask().addOnSuccessListener { snapshot ->
                continuation.resume(ExistQueuesDataResponse(getQueuesFromSnapshot(snapshot).map {
                    ExistQueuesDataResponse.Queue(it.id.orEmpty(), it.title.orEmpty(), it.description.orEmpty())
                }))
            }.addOnFailureListener {
                continuation.resume(ExistQueuesDataResponse(emptyList()))
            }
        }
    }

    override suspend fun applyToQueue(request: ApplyToQueueRequest): EmptyResponse {
        TODO("Not yet implemented")
    }
}