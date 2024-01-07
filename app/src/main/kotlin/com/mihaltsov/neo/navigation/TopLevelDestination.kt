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

package com.mihaltsov.neo.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.samples.apps.nowinandroid.core.designsystem.icon.NeoIcons
import com.mihaltsov.neo.feature.authorization.R as authorizationR
import com.mihaltsov.neo.feature.mainQueue.R as yourQueueR
import com.mihaltsov.neo.feature.checkIn.R as checkInR
import com.mihaltsov.neo.feature.queues.R as queuesR

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    QUEUES(
        selectedIcon = NeoIcons.Upcoming,
        unselectedIcon = NeoIcons.UpcomingBorder,
        iconTextId = queuesR.string.queues,
        titleTextId = queuesR.string.queues,
    ),
    AUTHORIZATION(
        selectedIcon = NeoIcons.Bookmarks,
        unselectedIcon = NeoIcons.BookmarksBorder,
        iconTextId = authorizationR.string.authorization,
        titleTextId = authorizationR.string.authorization,
    ),
    CHECK_IN(
        selectedIcon = NeoIcons.Bookmarks,
        unselectedIcon = NeoIcons.BookmarksBorder,
        iconTextId = checkInR.string.check_in_title,
        titleTextId = checkInR.string.check_in_title,
    ),
}
