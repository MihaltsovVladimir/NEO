plugins {
    alias(libs.plugins.neo.android.library)
    alias(libs.plugins.neo.android.hilt)
}
android {
    namespace = "com.mihaltsov.neo.core.common"
}
dependencies {
    implementation(libs.kotlinx.coroutines.android)
}