plugins {
    alias(libs.plugins.nowinandroid.android.library)
    alias(libs.plugins.nowinandroid.android.library.jacoco)
    alias(libs.plugins.nowinandroid.android.hilt)
    id("kotlinx-serialization")
}
android {
    namespace = "com.mihaltsov.core.data"
}
dependencies {
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.model)
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
}