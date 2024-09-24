plugins {
    id("com.toptal.library.kotlin")
    alias(libs.plugins.google.ksp)
}

dependencies {
    implementation(libs.coroutines.core)

    testImplementation(libs.junit4.core)
    testImplementation(libs.coroutines.core)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.tests.mockk)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
