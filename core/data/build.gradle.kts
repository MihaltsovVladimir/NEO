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

    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.model)
}