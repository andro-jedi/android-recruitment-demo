package com.toptal.repository

import com.toptal.core.common.resultOf
import com.toptal.data.database.entities.toDomain
import com.toptal.data.toDomain
import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.domain.helper.Result
import com.toptal.domain.repository.GitRepository
import com.toptal.repository.source.local.LocalDataSource
import com.toptal.repository.source.remote.RemoteDataSource
import com.toptal.repository.source.toDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : GitRepository {

    override fun getRepositories(user: String): Flow<Result<List<RepositoryItem>>> {
        return localDataSource.getRepositories(user)
            .map { Result.Success(it.map { it.toDomain() }) }
    }

    override suspend fun fetchRepositories(user: String): Result<List<RepositoryItem>> {
        return resultOf { remoteDataSource.getRepositories(user) }
            .onSuccess { localDataSource.saveRepositories(it.map { it.toDb() }) }
            .map { it.map { it.toDomain() } }
    }

    override suspend fun getRepositoryDetails(owner: String, name: String): Result<RepositoryDetails> {
        return resultOf { remoteDataSource.getRepositoryDetails(owner, name).toDomain() }
    }
}
