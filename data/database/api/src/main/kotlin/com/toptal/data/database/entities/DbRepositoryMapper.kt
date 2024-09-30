package com.toptal.data.database.entities

import com.toptal.domain.entities.list.RepositoryItem

fun DbRepositoryItem.toDomain(): RepositoryItem {
    return RepositoryItem(
        id = id,
        title = name,
        url = url,
        description = description
    )
}
