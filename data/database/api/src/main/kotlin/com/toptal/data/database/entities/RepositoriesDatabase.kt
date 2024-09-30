package com.toptal.data.database.entities

import kotlinx.coroutines.flow.Flow

interface RepositoriesDatabase {

    fun getRepositories(user: String): Flow<List<DbRepositoryItem>>

    suspend fun saveRepositories(repositories: List<DbRepositoryItem>)
}
