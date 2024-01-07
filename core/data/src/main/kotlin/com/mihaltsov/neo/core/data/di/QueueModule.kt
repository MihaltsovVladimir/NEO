package com.mihaltsov.neo.core.data.di

import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.data.repository.OfflineFirstPersonQueueRepository
import com.mihaltsov.neo.core.data.repository.OfflineFirstQueuesRepository
import com.mihaltsov.neo.core.data.repository.QueuesRepository
import com.mihaltsov.neo.core.network.NeoNetworkDataSource
import com.mihaltsov.neo.core.network.retrofit.RetrofitNeoNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface QueueModule {

    @Binds
    fun bindsPersonQueueRepository(repository: OfflineFirstPersonQueueRepository): QueueDataRepository

    @Binds
    fun bindsExistQueuesRepository(repository: OfflineFirstQueuesRepository): QueuesRepository

//    @Binds
//    fun bindsFakeNetworkDataSource(queueDataRepository: FakeNeoNetworkDataSource): NeoNetworkDataSource

    @Binds
    fun bindsNetworkDataSource(queueDataRepository: RetrofitNeoNetwork): NeoNetworkDataSource
}