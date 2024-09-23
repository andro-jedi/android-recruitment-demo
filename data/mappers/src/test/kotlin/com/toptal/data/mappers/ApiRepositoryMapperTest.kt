package com.toptal.data.mappers

import com.toptal.data.networking.ApiRepositoryDetails
import com.toptal.data.networking.ApiRepositoryIssue
import com.toptal.data.networking.ApiRepositoryItem
import com.toptal.data.networking.ApiRepositoryPr
import com.toptal.data.networking.Issues
import com.toptal.data.networking.PullRequests
import com.toptal.domain.entities.details.IssueState
import com.toptal.domain.entities.details.PrState
import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.entities.details.RepositoryIssue
import com.toptal.domain.entities.details.RepositoryPr
import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.mappers.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiRepositoryMapperTest {

    @Test
    fun `test ApiRepositoryItem toDomain`() {
        val apiRepositoryItem = ApiRepositoryItem(
            id = "1",
            name = "Test Repo",
            url = "https://github.com/test/test-repo",
            description = "A test repository"
        )
        val expected = RepositoryItem(
            id = "1",
            title = "Test Repo",
            url = "https://github.com/test/test-repo",
            description = "A test repository"
        )
        val actual = apiRepositoryItem.toDomain()
        assertEquals(expected, actual)
    }

    @Test
    fun `test ApiRepositoryDetails toDomain`() {
        val apiRepositoryDetails = ApiRepositoryDetails(
            id = "1",
            name = "Test Repo",
            url = "https://github.com/test/test-repo",
            issues = Issues(
                nodes = listOf(
                    ApiRepositoryIssue(
                        id = "1",
                        title = "Issue 1",
                        state = "open"
                    )
                ),
                totalCount = 1
            ),
            pullRequests = PullRequests(
                nodes = listOf(
                    ApiRepositoryPr(
                        id = "1",
                        title = "PR 1",
                        state = "merged"
                    )
                ),
                totalCount = 1
            )
        )
        val expected = RepositoryDetails(
            id = "1",
            title = "Test Repo",
            url = "https://github.com/test/test-repo",
            issues = listOf(
                RepositoryIssue(
                    id = "1",
                    title = "Issue 1",
                    state = IssueState.OPEN
                )
            ),
            pullRequests = listOf(
                RepositoryPr(
                    id = "1",
                    title = "PR 1",
                    state = PrState.MERGED
                )
            )
        )
        val actual = apiRepositoryDetails.toDomain()
        assertEquals(expected, actual)
    }

    @Test
    fun `test ApiRepositoryIssue toDomain`() {
        val apiRepositoryIssue = ApiRepositoryIssue(
            id = "1",
            title = "Issue 1",
            state = "open"
        )
        val expected = RepositoryIssue(
            id = "1",
            title = "Issue 1",
            state = IssueState.OPEN
        )
        val actual = apiRepositoryIssue.toDomain()
        assertEquals(expected, actual)
    }

    @Test
    fun `test ApiRepositoryPr toDomain`() {
        val apiRepositoryPr = ApiRepositoryPr(
            id = "1",
            title = "PR 1",
            state = "merged"
        )
        val expected = RepositoryPr(
            id = "1",
            title = "PR 1",
            state = PrState.MERGED
        )
        val actual = apiRepositoryPr.toDomain()
        assertEquals(expected, actual)
    }
}
