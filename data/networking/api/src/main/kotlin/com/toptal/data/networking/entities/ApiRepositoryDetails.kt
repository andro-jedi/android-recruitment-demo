package com.toptal.data.networking.entities

data class ApiRepositoryDetails(
    val id: String,
    val name: String,
    val url: String,
    val issues: Issues,
    val pullRequests: PullRequests
)

data class Issues(
    val nodes: List<ApiRepositoryIssue>,
    val totalCount: Int
)

data class PullRequests(
    val nodes: List<ApiRepositoryPr>,
    val totalCount: Int
)
