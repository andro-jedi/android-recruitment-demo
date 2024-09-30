package com.toptal.data.database.di

import android.app.Application
import androidx.room.Room
import com.toptal.data.database.GithubDatabase
import com.toptal.data.database.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRepositoryDao(db: GithubDatabase): RepositoryDao {
        return db.repositoryDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): GithubDatabase {
        return Room.databaseBuilder(
            app,
            GithubDatabase::class.java,
            "github.db"
        ).build()
    }
}
