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

package com.mihaltsov.neo.feature.mainQueue.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mihaltsov.neo.feature.mainQueue.YourQueueRoute
import java.net.URLDecoder

const val YOUR_QUEUE_ROUTE = "your_queue_route"
private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()
internal const val QUEUE_ID_ARG = "queueId"

internal class QueueArgs(val queueId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[QUEUE_ID_ARG]), URL_CHARACTER_ENCODING))
}

fun NavController.navigateToYourQueue(navOptions: NavOptions) {
    navigate(YOUR_QUEUE_ROUTE, navOptions)
}
//fun NavController.navigateToYourQueue(topicId: String) {
//    val encodedId = URLEncoder.encode(topicId, URL_CHARACTER_ENCODING)
//    navigate("$YOUR_QUEUE_ROUTE/$encodedId") {
//        launchSingleTop = true
//    }
//}

fun NavGraphBuilder.yourQueueScreen(
    onBackClick: () -> Unit,
) {
    composable(YOUR_QUEUE_ROUTE) {
        YourQueueRoute(
            onBackClick = onBackClick,
        )
    }
}