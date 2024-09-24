package com.toptal.domain.usecase

import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.domain.repository.GitRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetRepositoriesUsecaseTest {

    private val gitRepository: GitRepository = mockk()

    private lateinit var getRepositoriesUsecase: GetRepositoriesUsecase

    @Before
    fun setUp() {
        getRepositoriesUsecase = GetRepositoriesUsecase(gitRepository)
    }

    @Test
    fun `invoke should return list of RepositoryItem on success`() = runTest {
        // Arrange
        val user = "toptal"
        val repositories = listOf(
            RepositoryItem(id = "1", title = "repo1", url = "https://github.com/toptal/repo1"),
            RepositoryItem(id = "2", title = "repo2", url = "https://github.com/toptal/repo2"),
        )
        coEvery { gitRepository.getRepositories(user) } returns Result.success(repositories)

        // Act
        val result = getRepositoriesUsecase(user)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(repositories, result.getOrNull())
        coVerify { gitRepository.getRepositories(user) }
    }

    @Test
    fun `invoke should return error on failure`() = runTest {
        // Arrange
        val user = "toptal"
        val error = Exception("Error fetching repositories")
        coEvery { gitRepository.getRepositories(user) } returns Result.failure(error)

        // Act
        val result = getRepositoriesUsecase(user)

        // Assert
        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
        coVerify { gitRepository.getRepositories(user) }
    }
}
