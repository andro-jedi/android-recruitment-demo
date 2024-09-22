package com.toptal.data.networking

interface GithubApi {

    suspend fun getRepositoryDetails(
        owner: String,
        name: String,
    ): ApiRepositoryDetails

    suspend fun getRepositories(user: String): List<ApiRepositoryItem>
}
