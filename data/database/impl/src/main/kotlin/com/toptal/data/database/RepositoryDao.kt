package com.toptal.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.toptal.data.database.entities.DbRepositoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories")
    fun getAll(): Flow<List<DbRepositoryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<DbRepositoryItem>)

    @Query("DELETE FROM repositories")
    suspend fun deleteAll()

    @Transaction
    suspend fun clearAndInsert(items: List<DbRepositoryItem>) {
        deleteAll()
        insertAll(items)
    }
}
