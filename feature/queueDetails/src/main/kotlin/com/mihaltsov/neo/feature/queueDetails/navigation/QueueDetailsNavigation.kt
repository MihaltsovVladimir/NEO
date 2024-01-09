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

package com.mihaltsov.neo.feature.queueDetails.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.mihaltsov.neo.feature.queueDetails.QueueDetailsRoute
import java.net.URLDecoder
import java.net.URLEncoder

const val QUEUE_DETAILS_ROUTE = "queue_details_route"
private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()
internal const val QUEUE_ID_ARG = "queueId"
private const val DEEP_LINK_URI_PATTERN = "https://www.neo.by/queueDetails/{$QUEUE_ID_ARG}"

internal class QueueArgs(val queueId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[QUEUE_ID_ARG]), URL_CHARACTER_ENCODING))
}

fun NavController.navigateToQueueDetails(queueId: String) {
    val encodedId = URLEncoder.encode(queueId, URL_CHARACTER_ENCODING)
    navigate("$QUEUE_DETAILS_ROUTE/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.queueDetailsScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "$QUEUE_DETAILS_ROUTE/{$QUEUE_ID_ARG}",
        arguments = listOf(
            navArgument(QUEUE_ID_ARG) { type = NavType.StringType },
        ),
        deepLinks = listOf(navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) {
        QueueDetailsRoute(onBackClick = onBackClick)
    }
}