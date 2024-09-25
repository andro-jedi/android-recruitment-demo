package com.toptal.data.networking.entities

data class ApiRepositoryItem(
    val id: String,
    val name: String,
    val url: String,
    val description: String? = null
)
