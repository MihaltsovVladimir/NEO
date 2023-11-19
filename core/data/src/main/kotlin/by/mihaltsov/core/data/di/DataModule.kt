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

package by.mihaltsov.core.data.di

import by.mihaltsov.core.data.repository.TestHilt
import by.mihaltsov.core.data.repository.TestHiltImpl
import by.mihaltsov.core.data.repository.UserDataRepository
import by.mihaltsov.core.data.repository.fake.FakeOfflineFirstUserDataRepository
import by.mihaltsov.core.data.util.ConnectivityManagerNetworkMonitor
import by.mihaltsov.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(networkMonitor: ConnectivityManagerNetworkMonitor): NetworkMonitor

    @Binds
    fun bindsUserDataRepository(userDataRepository: FakeOfflineFirstUserDataRepository): UserDataRepository

    @Binds
    fun bindsFakeUserDataRepository(test: TestHiltImpl): TestHilt
}
