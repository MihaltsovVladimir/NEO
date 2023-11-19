package com.mihaltsov.neo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mihaltsov.core.designsystem.theme.NEOTheme
import com.mihaltsov.feature.authorization.AuthorizationRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(viewModel.dd)
        setContent {
            NEOTheme {
                val color = if (isSystemInDarkTheme()) Color.Green.copy(alpha = 0.3F) else MaterialTheme.colorScheme.background

                Surface(
                    modifier = Modifier,
                    color = color
                ) {
                    AuthorizationRoute(Modifier.fillMaxSize())
//                    YourQueueRoute(Modifier.fillMaxSize())
                }
            }
        }
    }
}