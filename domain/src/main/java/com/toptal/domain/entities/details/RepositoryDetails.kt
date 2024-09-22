package com.toptal.domain.entities.details

/**
 * Data class represents a detailed repository information
 */
data class RepositoryDetails(
    val id: String,
    val title: String,
    val url: String,
    val issues: List<RepositoryIssue>,
    val pullRequests: List<RepositoryPr>
)
