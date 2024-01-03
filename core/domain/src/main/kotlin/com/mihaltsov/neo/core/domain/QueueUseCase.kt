package com.mihaltsov.neo.core.domain

import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.data.repository.UserDataRepository
import com.mihaltsov.neo.core.model.QueueData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * A use case which returns selected queue and mark owner and user position
 */
class QueueUseCase @Inject constructor(
    private val userRepository: UserDataRepository,
    private val queueRepository: QueueDataRepository,
) {

    operator fun invoke(): Flow<QueueData> {
        return queueRepository.queueData.combine(userRepository.userData) { queueData, userData ->
            if (userData.queues.keys.contains(queueData.id)) {
                QueueData(
                    id = queueData.id,
                    persons = checkById(queueData.persons, userData.id)
                )
            } else queueData
        }
    }

    private fun checkById(persons: List<QueueData.Person>, userId: String): List<QueueData.Person> {
        return persons.map {
            if (it.id == userId) it.copy(isMine = true) else it
        }
    }
}