package com.toptal.repository

import com.toptal.core.common.resultOf
import com.toptal.data.networking.GithubApi
import com.toptal.data.toDomain
import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.domain.helper.Result
import com.toptal.domain.repository.GitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    private val api: GithubApi,
) : GitRepository {

    override fun getRepositories(user: String): Flow<Result<List<RepositoryItem>>> {
        return flow {
            emit(
                resultOf { api.getRepositories(user).map { it.toDomain() } },
            )
        }
    }

    override suspend fun getRepositoryDetails(owner: String, name: String): Result<RepositoryDetails> {
        return resultOf { api.getRepositoryDetails(owner, name).toDomain() }
    }
}
