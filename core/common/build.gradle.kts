plugins {
    alias(libs.plugins.nowinandroid.android.library)
    alias(libs.plugins.nowinandroid.android.hilt)
}
android {
    namespace = "com.mihaltsov.core.common"
}
dependencies {
    implementation(libs.kotlinx.coroutines.android)
}