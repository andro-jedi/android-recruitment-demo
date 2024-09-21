package com.toptal.data.di

import com.toptal.data.networking.Api
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ConnectionModule::class])
@InstallIn(SingletonComponent::class)
internal abstract class NetworkingModule {

    @Binds
    abstract fun api(impl: ApolloGraphQlApi): Api
}
