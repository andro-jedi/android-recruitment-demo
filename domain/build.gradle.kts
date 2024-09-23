plugins {
    id("com.toptal.library.kotlin")
    alias(libs.plugins.google.ksp)
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(projects.core.test)

    implementation(libs.junit4.core)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.test)
    implementation(libs.tests.mockk)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
