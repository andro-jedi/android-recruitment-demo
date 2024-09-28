package com.toptal.repository.source.remote

import com.toptal.data.networking.GithubApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val api: GithubApi,
) {

    suspend fun getRepositories(user: String) = api.getRepositories(user)

    suspend fun getRepositoryDetails(owner: String, name: String) = api.getRepositoryDetails(owner, name)
}
