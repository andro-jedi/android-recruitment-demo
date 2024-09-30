package com.toptal.repository.source

import com.toptal.data.database.entities.DbRepositoryItem
import com.toptal.data.networking.entities.ApiRepositoryItem

fun ApiRepositoryItem.toDb(): DbRepositoryItem {
    return DbRepositoryItem(
        id = id,
        name = name,
        url = url,
        description = description
    )
}
