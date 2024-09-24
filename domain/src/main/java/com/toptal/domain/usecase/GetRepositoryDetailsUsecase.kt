package com.toptal.domain.usecase

import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.helper.Result
import com.toptal.domain.repository.GitRepository
import javax.inject.Inject

/**
 * Usecase for getting repository details
 */
class GetRepositoryDetailsUsecase @Inject constructor(
    private val gitRepository: GitRepository
) {

    suspend operator fun invoke(owner: String, name: String): Result<RepositoryDetails> {
        return gitRepository.getRepositoryDetails(owner, name)
    }
}
