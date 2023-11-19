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

package com.mihaltsov.feature.mainQueue.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.mihaltsov.feature.mainQueue.YourQueueRoute

const val YOUR_QUEUE_RESOURCE_ID = "YourQueueResourceId"
const val YourQueueNavigationRoute = "for_you_route/{$YOUR_QUEUE_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN = "https://www.neo.by/queue/{$YOUR_QUEUE_RESOURCE_ID}"

fun NavController.navigateToForYou(navOptions: NavOptions? = null) {
    this.navigate(YourQueueNavigationRoute, navOptions)
}

fun NavGraphBuilder.queueScreen(onItemClick: (String) -> Unit) {
    composable(
        route = YourQueueNavigationRoute,
        deepLinks = listOf(navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },),
        arguments = listOf(navArgument(YOUR_QUEUE_RESOURCE_ID) { type = NavType.StringType },),
    ) {
        YourQueueRoute()
    }
}
