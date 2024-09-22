package com.toptal.github.presentation.repository.list

class RepositoriesListContract {
    data class State(
        val contentState: ContentState = ContentState.Progress,
        val items: List<UiRepositoryItem> = emptyList(),
    )

    sealed interface Event {
        data class ItemClicked(val name: String) : Event
        data object Retry : Event
    }

    sealed interface Effect {
        data class NavigateToDetails(val name: String) : Effect
    }
}

data class UiRepositoryItem(
    val id: String,
    val name: String,
    val url: String,
    val description: String? = null,
)

sealed class ContentState {

    data object Progress : ContentState()

    data object Success : ContentState()

    data class Error(val message: String) : ContentState()
}
