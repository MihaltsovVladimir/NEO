package com.mihaltsov.neo.core.data.di

import com.mihaltsov.neo.core.data.repository.QrRepository
import com.mihaltsov.neo.core.data.repository.fake.FakeQrRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface QrModule {

    @Binds
    fun bindsQrRepository(qrRepository: FakeQrRepository): QrRepository
}