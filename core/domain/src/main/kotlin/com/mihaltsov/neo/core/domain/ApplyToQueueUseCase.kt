package com.mihaltsov.neo.core.domain

import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.data.repository.UserDataRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ApplyToQueueUseCase @Inject constructor(
    private val userRepository: UserDataRepository,
    private val queueRepository: QueueDataRepository,
) {

    suspend fun applyToQueue(queueId: String) {
        userRepository.userData.onEach {
            queueRepository.applyToQueue(queueId = queueId, userData = it)
        }.collect()
    }
}