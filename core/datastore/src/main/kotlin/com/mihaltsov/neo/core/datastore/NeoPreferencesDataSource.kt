/*
 * Copyright 2022 The Android Open Source Project
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

package com.mihaltsov.neo.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import com.mihaltsov.core.model.UserData

class NeoPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data
        .map {
            UserData(
                nickName = "it.bookmarkedNewsResourceIdsMap.keys",
                phone = "it.viewedNewsResourceIdsMap.keys",
                registrationDate = "it.followedTopicIdsMap.keys",
                queueNumber = 1
            )
        }

    suspend fun setFollowedTopicIds(topicIds: Set<String>) {
        try {
            userPreferences.updateData {
                it.copy {
//                    followedTopicIds.clear()
//                    followedTopicIds.putAll(topicIds.associateWith { true })
//                    updateShouldHideOnboardingIfNecessary()
                }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
        }
    }

    suspend fun setTopicIdFollowed(topicId: String, followed: Boolean) {
        try {
            userPreferences.updateData {
                it.copy {
                    if (followed) {
//                        followedTopicIds.put(topicId, true)
                    } else {
//                        followedTopicIds.remove(topicId)
                    }
//                    updateShouldHideOnboardingIfNecessary()
                }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
        }
    }

    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
//        userPreferences.updateData {
//            it.copy {
//                this.useDynamicColor = useDynamicColor
//            }
//        }
    }


    suspend fun setNewsResourceBookmarked(newsResourceId: String, bookmarked: Boolean) {
//        try {
//            userPreferences.updateData {
//                it.copy {
//                    if (bookmarked) {
//                        bookmarkedNewsResourceIds.put(newsResourceId, true)
//                    } else {
//                        bookmarkedNewsResourceIds.remove(newsResourceId)
//                    }
//                }
//            }
//        } catch (ioException: IOException) {
//            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
//        }
    }

    suspend fun setNewsResourceViewed(newsResourceId: String, viewed: Boolean) {
        setNewsResourcesViewed(listOf(newsResourceId), viewed)
    }

    suspend fun setNewsResourcesViewed(newsResourceIds: List<String>, viewed: Boolean) {
//        userPreferences.updateData { prefs ->
//            prefs.copy {
//                newsResourceIds.forEach { id ->
//                    if (viewed) {
//                        viewedNewsResourceIds.put(id, true)
//                    } else {
//                        viewedNewsResourceIds.remove(id)
//                    }
//                }
//            }
//        }
    }


    /**
     * Update the [ChangeListVersions] using [update].
     */
    suspend fun updateChangeListVersion(update: ChangeListVersions.() -> ChangeListVersions) {
//        try {
//            userPreferences.updateData { currentPreferences ->
//                val updatedChangeListVersions = update(
//                    ChangeListVersions(
//                        topicVersion = currentPreferences.topicChangeListVersion,
//                        newsResourceVersion = currentPreferences.newsResourceChangeListVersion,
//                    ),
//                )
//
//                currentPreferences.copy {
//                    topicChangeListVersion = updatedChangeListVersions.topicVersion
//                    newsResourceChangeListVersion = updatedChangeListVersions.newsResourceVersion
//                }
//            }
//        } catch (ioException: IOException) {
//            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
//        }
    }

    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) {
//        userPreferences.updateData {
//            it.copy {
//                this.shouldHideOnboarding = shouldHideOnboarding
//            }
//        }
    }
}

//private fun UserPreferencesKt.Dsl.updateShouldHideOnboardingIfNecessary() {
//    if (followedTopicIds.isEmpty() && followedAuthorIds.isEmpty()) {
//        shouldHideOnboarding = false
//    }
//}
