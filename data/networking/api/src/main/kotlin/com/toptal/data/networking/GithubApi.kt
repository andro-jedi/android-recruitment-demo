package com.toptal.data.networking

import com.toptal.data.networking.entities.ApiRepositoryDetails
import com.toptal.data.networking.entities.ApiRepositoryItem

interface GithubApi {

    suspend fun getRepositoryDetails(
        owner: String,
        name: String,
    ): ApiRepositoryDetails

    suspend fun getRepositories(user: String): List<ApiRepositoryItem>
}
