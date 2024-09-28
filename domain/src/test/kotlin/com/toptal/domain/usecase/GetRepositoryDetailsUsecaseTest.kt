package com.toptal.domain.usecase

import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.exception.GeneralError
import com.toptal.domain.helper.Result
import com.toptal.domain.repository.GitRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetRepositoryDetailsUsecaseTest {

    private val gitRepository: GitRepository = mockk()

    private lateinit var getRepositoryDetailsUsecase: GetRepositoryDetailsUsecase

    @Before
    fun setUp() {
        getRepositoryDetailsUsecase = GetRepositoryDetailsUsecase(gitRepository)
    }

    @Test
    fun `invoke should return RepositoryDetails on success`() = runTest {
        // Arrange
        val owner = "toptal"
        val name = "repository"
        val repositoryDetails = RepositoryDetails(
            id = "1",
            title = name,
            url = "https://github.com/toptal/$name",
            issues = emptyList(),
            pullRequests = emptyList(),
        )
        coEvery { gitRepository.getRepositoryDetails(owner, name) } returns Result.Success(repositoryDetails)

        // Act
        val result = getRepositoryDetailsUsecase(owner, name)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(repositoryDetails, result.getOrNull())
        coVerify { gitRepository.getRepositoryDetails(owner, name) }
    }

    @Test
    fun `invoke should return error on failure`() = runTest {
        // Arrange
        val owner = "toptal"
        val name = "repository"
        val error = GeneralError.Network.Unknown
        coEvery { gitRepository.getRepositoryDetails(owner, name) } returns Result.Failure(error)

        // Act
        val result = getRepositoryDetailsUsecase(owner, name)

        // Assert
        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
        coVerify { gitRepository.getRepositoryDetails(owner, name) }
    }
}

