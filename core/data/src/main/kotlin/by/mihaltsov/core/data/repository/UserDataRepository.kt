package by.mihaltsov.core.data.repository

import by.mihaltsov.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>
}