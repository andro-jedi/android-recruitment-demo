plugins {
    id("com.toptal.library.kotlin")
}

dependencies {
    implementation(projects.data.networking.api)
    implementation(projects.domain)

    implementation(libs.junit4.core)
}
