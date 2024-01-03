/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mihaltsov.neo.core.data.repository

import com.mihaltsov.neo.core.data.util.Synchronizer
import com.mihaltsov.neo.core.data.util.changeUserDataSync
import com.mihaltsov.neo.core.datastore.NeoPreferencesDataSource
import com.mihaltsov.neo.core.model.UserData
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.mapper.mapToData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val network: NeoNetworkDataSource,
    private val neoPreferencesDataSource: NeoPreferencesDataSource
) : UserDataRepository {

    override val userData: Flow<UserData> = neoPreferencesDataSource.userData

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {

        return synchronizer.changeUserDataSync(
            networkData = network.userData().mapToData(),
            modelUpdater = neoPreferencesDataSource::setUpUserData
        )
    }
}
