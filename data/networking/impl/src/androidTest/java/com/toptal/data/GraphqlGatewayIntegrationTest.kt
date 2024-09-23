package com.toptal.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toptal.data.networking.GithubApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileNotFoundException
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
internal class GraphqlGatewayIntegrationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var api: GithubApi

    @Inject
    lateinit var server: MockWebServer

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun pulls_repository_details() = runTest {
        server.enqueue(mockJson("details.json"))
        val repoName = "gitignore"
        val result = api.getRepositoryDetails(
            owner = "toptal",
            name = repoName,
        )

        assertThat(result.id).isEqualTo("MDEwOlJlcG9zaXRvcnkxMDYyODk3")
        assertThat(result.name).isEqualTo(repoName)
    }

    private fun mockJson(fileName: String): MockResponse {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
            ?: throw FileNotFoundException("Resource not found: $fileName")
        val content = inputStream.bufferedReader().use { it.readText() }
        return MockResponse().apply {
            setBody(content)
        }
    }
}

