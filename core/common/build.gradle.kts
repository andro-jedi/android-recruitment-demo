plugins {
    id("com.toptal.library.kotlin")
    alias(libs.plugins.google.ksp)
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(projects.domain)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
