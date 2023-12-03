package com.mihaltsov.neo.core.data.repository.fake

import com.mihaltsov.neo.core.data.repository.UserDataRepository
import com.mihaltsov.neo.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeOfflineFirstUserDataRepository @Inject constructor() : UserDataRepository {

    override val userData: Flow<UserData> = flowOf(
        UserData(
            nickName = "Mihaltsov",
            phone = "+375445504442",
            registrationDate = "ddd",
            queueNumber = 1
        )
    )
}