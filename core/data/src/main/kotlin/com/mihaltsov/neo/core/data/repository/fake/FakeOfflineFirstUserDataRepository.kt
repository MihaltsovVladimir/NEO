package com.mihaltsov.neo.core.data.repository.fake

import com.mihaltsov.neo.core.data.repository.UserDataRepository
import com.mihaltsov.neo.core.data.util.Synchronizer
import com.mihaltsov.neo.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeOfflineFirstUserDataRepository @Inject constructor() : UserDataRepository {

    override val userData: Flow<UserData> = flowOf(
        UserData(
            id = "id",
            nickName = "Mihaltsov",
            phone = "+375445504442",
            queues = emptyList()
        )
    )

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        return true
    }
}