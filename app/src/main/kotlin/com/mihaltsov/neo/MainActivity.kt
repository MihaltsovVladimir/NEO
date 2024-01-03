package com.mihaltsov.neo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.mihaltsov.neo.core.data.repository.UserDataRepository
import com.mihaltsov.neo.core.data.util.NetworkMonitor
import com.mihaltsov.neo.core.designsystem.theme.NeoTheme
import com.mihaltsov.neo.ui.NeoApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var userDataRepository: UserDataRepository

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoTheme {
                NeoApp(
                    networkMonitor = networkMonitor,
                    windowSizeClass = calculateWindowSizeClass(this),
                    userNewsResourceRepository = userDataRepository,
                )
            }
        }
    }
}