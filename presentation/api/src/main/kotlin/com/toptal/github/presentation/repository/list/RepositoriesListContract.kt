package com.toptal.github.presentation.repository.list

class RepositoriesListContract {
    data class State(
        val showProgress: Boolean = true,
        val items: List<UiRepositoryItem> = emptyList()
    )

    sealed interface Event {
        data class ItemClicked(val id: String) : Event
        data object Refresh : Event
    }

    sealed interface Effect {
       data class NavigateToDetails(val id: String) : Effect
    }
}

data class UiRepositoryItem(
    val id: String,
    val name: String,
    val url: String,
    val description: String? = null,
)
