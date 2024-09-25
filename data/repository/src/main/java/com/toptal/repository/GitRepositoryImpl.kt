package com.toptal.repository

import com.toptal.data.networking.GithubApi
import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.domain.repository.GitRepository
import com.toptal.core.common.resultOf
import com.toptal.data.toDomain
import com.toptal.domain.helper.Result
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    private val api: GithubApi,
) : GitRepository {

    override suspend fun getRepositories(user: String): Result<List<RepositoryItem>> {
        return resultOf {
            api.getRepositories(user).map { it.toDomain() }
        }
    }

    override suspend fun getRepositoryDetails(owner: String, name: String): Result<RepositoryDetails> {
        return resultOf {
            api.getRepositoryDetails(owner, name).toDomain()
        }
    }
}
