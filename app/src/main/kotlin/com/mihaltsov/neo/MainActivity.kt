package com.mihaltsov.neo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mihaltsov.core.domain.FirstUseCase
import com.mihaltsov.core.ui.FirstUiView
import com.mihaltsov.feature.mainQueue.MainQueueScreen
import com.mihaltsov.neo.ui.theme.NEOTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirstUseCase()
        FirstUiView()
        setContent {
            NEOTheme {
                val color = if (isSystemInDarkTheme()) {
                    Color.Green.copy(alpha = 0.3F)
                } else {
                    MaterialTheme.colorScheme.background
                }
                Surface(
                    modifier = Modifier,
                    color = color
                ) {
                    MainQueueScreen(Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val res = stringResource(id = R.string.app_name)
    Text(
        text = "Hello $res $name!",
        modifier = modifier.padding(start = 32.dp),
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    NEOTheme {
        Greeting("Android")
    }
}