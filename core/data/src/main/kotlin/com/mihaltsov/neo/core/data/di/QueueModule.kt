package com.mihaltsov.neo.core.data.di

import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.data.repository.fake.FakeOfflineFirstUserQueueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface QueueModule {

    @Binds
    fun bindsQueussseRepository(queuessDataRepository: FakeOfflineFirstUserQueueRepository): QueueDataRepository
}