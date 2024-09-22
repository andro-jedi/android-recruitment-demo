package com.toptal.github.presentation.repository.list

data class UiRepositoryList(
    val items: List<UiRepositoryItem> = emptyList(),
)

sealed class UiRepositoryItem {

    data object Progress : UiRepositoryItem()

    data class Error(val onRetryClicked: () -> Unit) : UiRepositoryItem()

    data class Repository(
        val name: String,
        val url: String,
        val onClicked: () -> Unit,
    ) : UiRepositoryItem()
}
