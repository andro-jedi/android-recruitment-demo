package com.toptal.github.presentation.repository

import com.toptal.domain.entities.details.IssueState
import com.toptal.domain.entities.details.PrState
import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.github.presentation.repository.details.UiIssue
import com.toptal.github.presentation.repository.details.UiPullRequest
import com.toptal.github.presentation.repository.details.UiRepositoryDetails
import com.toptal.github.presentation.repository.list.UiRepositoryItem

fun RepositoryItem.toUi(): UiRepositoryItem {
    return UiRepositoryItem(
        id = id,
        name = title,
        url = url,
        description = description,
    )
}

fun RepositoryDetails.toUi(): UiRepositoryDetails {
    val openIssues = issues.filter { it.state == IssueState.OPEN }.map { UiIssue(id = it.id, title = it.title) }
    val openPrs = pullRequests.filter { it.state == PrState.OPEN }.map { UiPullRequest(id = it.id, title = it.title) }
    return UiRepositoryDetails(
        id = id,
        title = title,
        url = url,
        openIssuesCount = openIssues.size,
        closedIssuesCount = issues.count { it.state == IssueState.CLOSED },
        openPullRequestsCount = openPrs.size,
        closedPullRequestsCount =  pullRequests.count { it.state == PrState.CLOSED },
        openIssues = openIssues,
        openPullRequests = openPrs,
    )
}
