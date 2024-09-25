package com.toptal.data

import com.toptal.data.networking.entities.ApiRepositoryDetails
import com.toptal.data.networking.entities.ApiRepositoryIssue
import com.toptal.data.networking.entities.ApiRepositoryItem
import com.toptal.data.networking.entities.ApiRepositoryPr
import com.toptal.domain.entities.details.IssueState
import com.toptal.domain.entities.details.PrState
import com.toptal.domain.entities.details.RepositoryDetails
import com.toptal.domain.entities.details.RepositoryIssue
import com.toptal.domain.entities.details.RepositoryPr
import com.toptal.domain.entities.list.RepositoryItem

fun ApiRepositoryItem.toDomain(): RepositoryItem {
    return RepositoryItem(
        id = id,
        title = name,
        url = url,
        description = description
    )
}

fun ApiRepositoryDetails.toDomain(): RepositoryDetails {
    return RepositoryDetails(
        id = id,
        title = name,
        url = url,
        issues = issues.nodes.map { it.toDomain() },
        pullRequests = pullRequests.nodes.map { it.toDomain() }
    )
}

fun ApiRepositoryIssue.toDomain(): RepositoryIssue {
    return RepositoryIssue(
        id = id,
        title = title,
        state = IssueState.valueOf(state.uppercase())
    )
}

fun ApiRepositoryPr.toDomain(): RepositoryPr {
    return RepositoryPr(
        id = id,
        title = title,
        state = PrState.valueOf(state.uppercase())
    )
}
