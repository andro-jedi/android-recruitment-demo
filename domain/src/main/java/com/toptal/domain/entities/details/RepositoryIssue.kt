package com.toptal.domain.entities.details

data class RepositoryIssue(
    val id: String,
    val title: String,
    val state: IssueState
)

enum class IssueState {
    OPEN,
    CLOSED
}
