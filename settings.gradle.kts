/*
 * Copyright 2021 The Android Open Source Project
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

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NEO"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:datastore")
include(":core:database")
include(":core:common")
include(":core:model")
include(":core:designsystem")
include(":core:network")
include(":sync")

include(":feature:mainQueue")
include(":feature:authorization")
include(":feature:checkIn")
include(":feature:queues")

include(":sync:work")