package com.toptal.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toptal.data.database.entities.DbRepositoryItem

@Database(entities = [DbRepositoryItem::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}
