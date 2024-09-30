package com.toptal.repository.source.remote

import com.toptal.data.networking.GithubApi
import com.toptal.data.networking.entities.ApiRepositoryDetails
import com.toptal.data.networking.entities.ApiRepositoryItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val api: GithubApi,
) {

    suspend fun getRepositories(user: String): List<ApiRepositoryItem> {
        return api.getRepositories(user)
    }

    suspend fun getRepositoryDetails(owner: String, name: String): ApiRepositoryDetails {
        return api.getRepositoryDetails(owner, name)
    }
}
