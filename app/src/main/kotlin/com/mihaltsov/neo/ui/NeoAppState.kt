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

package com.mihaltsov.neo.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.mihaltsov.neo.core.data.repository.UserDataRepository
import com.mihaltsov.neo.core.data.util.NetworkMonitor
import com.mihaltsov.neo.feature.authorization.navigation.AUTH_ROUTE
import com.mihaltsov.neo.feature.authorization.navigation.navigateToaAuthorization
import com.mihaltsov.neo.feature.checkin.navigation.CHECK_IN_ROUTE
import com.mihaltsov.neo.feature.checkin.navigation.navigateToCheckIn
import com.mihaltsov.neo.feature.queues.navigation.QUEUES_ROUTE
import com.mihaltsov.neo.feature.queues.navigation.navigateToQueuesGraph
import com.mihaltsov.neo.navigation.TopLevelDestination
import com.mihaltsov.neo.navigation.TopLevelDestination.AUTHORIZATION
import com.mihaltsov.neo.navigation.TopLevelDestination.CHECK_IN
import com.mihaltsov.neo.navigation.TopLevelDestination.QUEUES
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberNeoAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    userNewsResourceRepository: UserDataRepository,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): NeoAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor,
        userNewsResourceRepository,
    ) {
        NeoAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            networkMonitor,
            userNewsResourceRepository,
        )
    }
}

@Stable
class NeoAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    userDataRepository: UserDataRepository,
) {

    val currentDestination: NavDestination? @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            QUEUES_ROUTE -> QUEUES
            CHECK_IN_ROUTE -> CHECK_IN
            AUTH_ROUTE -> AUTHORIZATION
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries


    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                AUTHORIZATION -> navController.navigateToaAuthorization(topLevelNavOptions)
                CHECK_IN -> navController.navigateToCheckIn(topLevelNavOptions)
                QUEUES -> navController.navigateToQueuesGraph(topLevelNavOptions)
            }
        }
    }

    /**
     * The top level destinations that have unread news resources.
     */
    val topLevelDestinationsWithUnreadResources: StateFlow<Set<TopLevelDestination>> =
        flowOf(
            setOfNotNull(
//                YOUR_QUEUE,
                AUTHORIZATION,
            )
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5_000),
            initialValue = emptySet(),
        )
}
