plugins {
    id("com.toptal.library.kotlin")
    alias(libs.plugins.google.ksp)
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(projects.core.test)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
