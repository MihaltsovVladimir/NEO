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

package com.mihaltsov.neo.feature.queues.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mihaltsov.neo.feature.queues.QueuesRoute

const val QUEUES_GRAPH_ROUTE_PATTERN = "queues_graph"
const val QUEUES_ROUTE = "queues_route"

fun NavController.navigateToQueuesGraph(navOptions: NavOptions) {
    navigate(QUEUES_ROUTE, navOptions)
}

fun NavGraphBuilder.queuesGraph(
    onQueueClick: (String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    composable(QUEUES_ROUTE) {
        QueuesRoute()
    }

}