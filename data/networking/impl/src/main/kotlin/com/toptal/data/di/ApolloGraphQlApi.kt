package com.toptal.data.di

import com.apollographql.apollo.ApolloClient
import com.toptal.data.networking.ApiRepositoryDetails
import com.toptal.data.networking.ApiRepositoryIssue
import com.toptal.data.networking.ApiRepositoryItem
import com.toptal.data.networking.ApiRepositoryPr
import com.toptal.data.networking.GithubApi
import com.toptal.data.networking.Issues
import com.toptal.data.networking.PullRequests
import com.toptal.graphql.RepositoriesListQuery
import com.toptal.graphql.RepositoryDetailsQuery
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

internal class ApolloGraphQlApi @Inject constructor(
    private val client: ApolloClient,
) : GithubApi {

    override suspend fun getRepositoryDetails(owner: String, name: String): ApiRepositoryDetails {
        val response = client.query(
            RepositoryDetailsQuery(
                owner = owner,
                name = name,
            ),
        ).execute()

        val data = response.data?.repository
        return if (data == null) {
            throw IllegalStateException("Missing exception handling", response.exception)
        } else {
            ApiRepositoryDetails(
                id = data.id,
                name = data.name,
                url = data.url.toString(),
                issues = Issues(
                    nodes = data.issues.nodes?.mapNotNull {
                        it?.let { ApiRepositoryIssue(it.id, it.title, it.state.toString()) }
                    } ?: emptyList(),
                    totalCount = data.issues.totalCount,
                ),
                pullRequests = PullRequests(
                    nodes = data.pullRequests.nodes?.mapNotNull {
                        it?.let { ApiRepositoryPr(it.id, it.title, it.state.toString()) }
                    } ?: emptyList(),
                    totalCount = data.pullRequests.totalCount,
                ),
            )
        }
    }

    override suspend fun getRepositories(user: String): List<ApiRepositoryItem> {
        val response = client.query(
            RepositoriesListQuery(owner = user),
        )
            .toFlow()
            .toList()
            .last()

        val data = response.data?.repositoryOwner?.repositories
        return if (data == null) {
            throw IllegalStateException("Missing exception handling", response.exception)
        } else {
            data.nodes?.mapNotNull {
                it?.let {
                    ApiRepositoryItem(
                        id = it.id,
                        name = it.name,
                        url = it.url.toString(),
                        description = it.description,
                    )
                }
            } ?: emptyList()
        }
    }
}
