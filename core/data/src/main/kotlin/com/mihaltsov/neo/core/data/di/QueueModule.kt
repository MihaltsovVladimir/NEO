package com.mihaltsov.neo.core.data.di

import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.data.repository.fake.FakeOfflineFirstUserQueueRepository
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.fake.FakeNeoNetworkDataSource
import com.mihaltsov.neo.core.network.retrofit.RetrofitNeoNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface QueueModule {

    @Binds
    fun bindsQueueRepository(queueDataRepository: FakeOfflineFirstUserQueueRepository): QueueDataRepository

    @Binds
    fun bindsFakeNetworkDataSource(queueDataRepository: FakeNeoNetworkDataSource): NeoNetworkDataSource

//    @Binds
//    fun bindsNetworkDataSource(queueDataRepository: RetrofitNeoNetwork): NeoNetworkDataSource
}