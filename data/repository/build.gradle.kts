plugins {
    id("com.toptal.library.android")
    alias(libs.plugins.google.ksp)
}

dependencies {
    implementation(projects.data.database.api)
    implementation(projects.data.database.impl)

    implementation(projects.data.networking.api)
    implementation(projects.data.networking.impl)
    implementation(projects.domain)
    implementation(projects.core.common)

    implementation(libs.coroutines.core)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
