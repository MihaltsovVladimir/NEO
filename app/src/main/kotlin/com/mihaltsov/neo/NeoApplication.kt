package com.mihaltsov.neo

import android.app.Application
import com.mihaltsov.neo.sync.initializers.Sync
import dagger.hilt.android.HiltAndroidApp

/**
 * [Application] class for Neo
 */
@HiltAndroidApp
class NeoApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        // Initialize Sync; the system responsible for keeping data in the app up to date.
        Sync.initialize(context = this)
    }
}