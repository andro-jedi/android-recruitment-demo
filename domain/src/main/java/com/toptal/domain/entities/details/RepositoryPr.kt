package com.toptal.domain.entities.details

data class RepositoryPr(
    val title: String,
    val status: PrStatus
)

enum class PrStatus {
    OPEN,
    CLOSED
}
