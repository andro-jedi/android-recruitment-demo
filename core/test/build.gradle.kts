plugins {
    id("com.toptal.library.kotlin")
}

dependencies {
    api(projects.core.common)
    api(libs.junit4.core)
    api(libs.coroutines.core)
    api(libs.coroutines.test)
    api(libs.tests.mockk)
}
