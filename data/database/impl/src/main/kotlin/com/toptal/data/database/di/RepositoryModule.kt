package com.toptal.data.database.di

import com.toptal.data.database.RepositoriesDatabaseImpl
import com.toptal.data.database.entities.RepositoriesDatabase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepositoriesDatabase(impl: RepositoriesDatabaseImpl): RepositoriesDatabase
}
