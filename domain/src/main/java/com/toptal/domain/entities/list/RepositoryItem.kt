package com.toptal.domain.entities.list

/**
 * Data class represents an item in the list of repositories
 */
data class RepositoryItem(
    val id: String,
    val title: String,
    val url: String,
    val description: String? = null,
)
