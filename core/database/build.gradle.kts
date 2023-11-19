plugins {
    alias(libs.plugins.nowinandroid.android.library)
    alias(libs.plugins.nowinandroid.android.library.jacoco)
    alias(libs.plugins.nowinandroid.android.hilt)
    alias(libs.plugins.nowinandroid.android.room)
}
android {
    namespace = "com.mihaltsov.core.database"
}
dependencies {
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
}