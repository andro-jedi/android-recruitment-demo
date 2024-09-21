plugins {
    id("com.toptal.library.android")
    alias(libs.plugins.compose.compiler)
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.material3)
}
