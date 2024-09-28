package com.toptal.domain.repository

import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.domain.helper.Result
import kotlinx.coroutines.flow.Flow

/**
 * Interface for Git repository
 */
interface GitRepository {

    /**
     * Get list of repositories for user
     *
     * @param user user name
     */
    fun getRepositories(user: String) : Flow<Result<List<RepositoryItem>>>

    /**
     * Get details of repository
     *
     * @param owner owner of the repository
     * @param name name of the repository
     */
    suspend fun getRepositoryDetails(owner: String, name: String) : Result<RepositoryDetails>
}
