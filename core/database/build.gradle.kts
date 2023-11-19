plugins {
    alias(libs.plugins.neo.android.library)
    alias(libs.plugins.neo.android.library.jacoco)
    alias(libs.plugins.neo.android.hilt)
    alias(libs.plugins.neo.android.room)
}
android {
    namespace = "com.mihaltsov.core.database"
}
dependencies {
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
}