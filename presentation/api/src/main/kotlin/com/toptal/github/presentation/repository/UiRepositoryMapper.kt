package com.toptal.github.presentation.repository

import com.toptal.domain.entities.list.RepositoryItem
import com.toptal.github.presentation.repository.list.UiRepositoryItem

fun RepositoryItem.toUi(): UiRepositoryItem {
    return UiRepositoryItem(
        id = id,
        name = title,
        url = url,
        description = description,
    )
}
