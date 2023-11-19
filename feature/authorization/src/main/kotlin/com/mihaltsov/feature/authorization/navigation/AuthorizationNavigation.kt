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

package com.mihaltsov.feature.authorization.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mihaltsov.feature.authorization.AuthorizationRoute

const val AUTHORIZATION = "Authorization"
const val AuthorizationNavigationRoute = "authorization/{$AUTHORIZATION}"

fun NavController.navigateToaAuthorization(navOptions: NavOptions? = null) {
    this.navigate(AuthorizationNavigationRoute, navOptions)
}

fun NavGraphBuilder.authorizationScreen(onItemClick: (String) -> Unit) {
    composable(
        route = AuthorizationNavigationRoute,
        arguments = listOf(navArgument(AUTHORIZATION) { type = NavType.StringType }),
    ) {
        AuthorizationRoute()
    }
}
