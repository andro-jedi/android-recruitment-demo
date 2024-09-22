package com.toptal.data.networking

data class ApiRepositoryItem(
    val id: String,
    val name: String,
    val url: String,
    val description: String? = null
)
