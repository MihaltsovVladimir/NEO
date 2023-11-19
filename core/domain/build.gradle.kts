plugins {
    alias(libs.plugins.nowinandroid.android.library)
    alias(libs.plugins.nowinandroid.android.library.jacoco)
    kotlin("kapt")
}
android {
    namespace = "com.mihaltsov.core.domain"
}
dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)

    kapt(libs.hilt.compiler)
}