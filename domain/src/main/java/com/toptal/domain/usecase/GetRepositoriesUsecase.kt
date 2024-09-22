package com.toptal.domain.usecase

import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.domain.repository.GitRepository
import javax.inject.Inject

/**
 * Usecase for getting list of repositories
 */
class GetRepositoriesUsecase @Inject constructor(
    private val gitRepository: GitRepository
) {

    suspend operator fun invoke(user: String = "toptal"): Result<List<RepositoryItem>> {
        return gitRepository.getRepositories(user)
    }
}
