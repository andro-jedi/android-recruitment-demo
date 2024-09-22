plugins {
    id("com.toptal.library.kotlin")
    alias(libs.plugins.google.ksp)
}

dependencies {
    implementation(projects.data.networking.api)
    implementation(projects.data.mappers)
    implementation(projects.domain)
    implementation(projects.core.common)

    implementation(libs.coroutines.core)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
