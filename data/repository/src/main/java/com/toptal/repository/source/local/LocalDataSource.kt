package com.toptal.repository.source.local

import com.toptal.data.database.entities.DbRepositoryItem
import com.toptal.data.database.entities.RepositoriesDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val database: RepositoriesDatabase
) {

    fun getRepositories(user: String): Flow<List<DbRepositoryItem>> = database.getRepositories(user)

    suspend fun saveRepositories(repositories: List<DbRepositoryItem>) = database.saveRepositories(repositories)
}
