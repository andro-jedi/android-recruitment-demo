package com.toptal.domain.entities.details

data class RepositoryPr(
    val id: String,
    val title: String,
    val state: PrState
)

enum class PrState {
    OPEN,
    CLOSED
}
