plugins {
    alias(libs.plugins.neo.android.library)
    alias(libs.plugins.neo.android.library.jacoco)
    alias(libs.plugins.neo.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.mihaltsov.neo.core.data"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.database.ktx)

    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.core.database)
    implementation(project(":core:realtime"))
}