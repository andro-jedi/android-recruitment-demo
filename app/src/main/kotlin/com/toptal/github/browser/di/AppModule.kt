package com.toptal.github.browser.di

import com.toptal.data.networking.GithubConfig
import com.toptal.github.browser.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubConfig(): GithubConfig {
        return GithubConfig(
            token = BuildConfig.GITHUB_TOKEN,
            url = BuildConfig.GITHUB_API,
        )
    }
}
