plugins {
    alias(libs.plugins.neo.android.library)
    alias(libs.plugins.neo.android.library.jacoco)
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