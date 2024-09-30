package com.toptal.data.database

import com.toptal.data.database.entities.DbRepositoryItem
import com.toptal.data.database.entities.RepositoriesDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoriesDatabaseImpl @Inject internal constructor(
    private val repositoryDao: RepositoryDao
) : RepositoriesDatabase {

    override fun getRepositories(user: String): Flow<List<DbRepositoryItem>> {
        return repositoryDao.getAll()
    }

    override suspend fun saveRepositories(repositories: List<DbRepositoryItem>) {
        repositoryDao.clearAndInsert(repositories)
    }
}
