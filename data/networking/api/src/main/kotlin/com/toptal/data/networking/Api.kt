package com.toptal.data.networking

interface Api {

    suspend fun getRepositoryDetails(request: ApiRepositoryRequest): ApiRepositoryDetails

    suspend fun getRepositories(user: String): List<ApiRepositoryDetails>
}

data class ApiRepositoryDetails(
    val id: String,
    val name: String,
    val url: String,
)

data class ApiRepositoryRequest(
    val owner: String,
    val name: String,
)
