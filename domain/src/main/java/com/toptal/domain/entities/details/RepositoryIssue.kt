package com.toptal.domain.entities.details

data class RepositoryIssue(
    val title: String,
    val status: IssueStatus
)

enum class IssueStatus {
    OPEN,
    CLOSED
}
