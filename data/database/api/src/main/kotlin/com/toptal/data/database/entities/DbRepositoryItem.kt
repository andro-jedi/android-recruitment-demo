package com.toptal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class DbRepositoryItem(
    @PrimaryKey val id: String,
    val name: String,
    val url: String,
    val description: String? = null
)
