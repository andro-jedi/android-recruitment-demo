plugins {
    id("com.toptal.library.kotlin")
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.junit4.core)
    implementation(libs.coroutines.test)
}
