package com.toptal.domain.usecase

import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.domain.helper.Result
import com.toptal.domain.repository.GitRepository
import javax.inject.Inject

/**
 * Usecase for fetching list of repositories and saving them to local database
 */
class FetchRepositoriesUsecase @Inject constructor(
    private val gitRepository: GitRepository,
) {

    suspend operator fun invoke(user: String = "toptal"): Result<List<RepositoryItem>> {
        return gitRepository.fetchRepositories(user)
    }
}
