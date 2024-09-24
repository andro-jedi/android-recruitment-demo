package com.toptal.github.presentation.repository.details

import com.toptal.domain.exception.DomainError

class RepositoryDetailsContract {
    data class State(
        val repositoryDetails: UiRepositoryDetails,
        val contentState: ContentState = ContentState.Progress,
    )

    sealed interface Event {
        data object Retry : Event
    }

    sealed interface Effect {
        data object NavigateBack : Effect
    }
}

data class UiRepositoryDetails(
    val id: String,
    val title: String,
    val url: String,
    val openIssuesCount: Int = 0,
    val closedIssuesCount: Int = 0,
    val openPullRequestsCount: Int = 0,
    val closedPullRequestsCount: Int = 0,
    val openIssues: List<UiIssue> = emptyList(),
    val openPullRequests: List<UiPullRequest> = emptyList(),
) {

    companion object {

        val EMPTY = UiRepositoryDetails(id = "", title = "", url = "")
    }
}

data class UiIssue(val id: String, val title: String)
data class UiPullRequest(val id: String, val title: String)

sealed class ContentState {

    data object Progress : ContentState()

    data object Success : ContentState()

    data class Error(val cause: DomainError) : ContentState()
}
