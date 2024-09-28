package com.toptal.domain.usecase

import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.domain.helper.Result
import com.toptal.domain.repository.GitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Usecase for getting list of repositories
 */
class GetRepositoriesUsecase @Inject constructor(
    private val gitRepository: GitRepository,
) {

    operator fun invoke(user: String = "toptal"): Flow<Result<List<RepositoryItem>>> {
        return gitRepository.getRepositories(user)
    }
}
