package com.toptal.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo.cache.normalized.normalizedCache
import com.apollographql.apollo.network.okHttpClient
import com.toptal.data.di.ApolloGraphQlApi
import com.toptal.data.di.NetworkingModule
import com.toptal.data.networking.GithubApi
import com.toptal.data.networking.GithubConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkingModule::class]
)
object TestNetworkingModule {

    @Provides
    @Singleton
    fun provideMockWebServer(): MockWebServer = MockWebServer()

    @Provides
    @Singleton
    fun provideGithubConfig(mockWebServer: MockWebServer): GithubConfig {
        return GithubConfig(
            url = mockWebServer.url("/").toString(),
            token = "dummyToken"
        )
    }

    @Provides
    @Singleton
    fun provideGithubApi(
        config: GithubConfig,
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): GithubApi {
        val apolloClient = ApolloClient.Builder()
            .serverUrl(config.url)
            .okHttpClient(
                OkHttpClient.Builder().apply {
                    interceptors.forEach { addInterceptor(it) }
                }.build()
            )
            .normalizedCache(MemoryCacheFactory(maxSizeBytes = 1024 * 1024))
            .build()

        return ApolloGraphQlApi(apolloClient)
    }
}
