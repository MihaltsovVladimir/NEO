package com.mihaltsov.core.data.di

import com.mihaltsov.core.data.repository.QueueDataRepository
import com.mihaltsov.core.data.repository.fake.FakeOfflineFirstUserQueueRepository
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