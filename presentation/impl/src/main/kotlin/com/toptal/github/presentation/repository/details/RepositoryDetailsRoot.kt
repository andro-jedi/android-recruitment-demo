package com.toptal.github.presentation.repository.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.toptal.design.ToptalTheme
import com.toptal.domain.exception.GeneralError
import com.toptal.domain.exception.DomainError

@Composable
fun RepositoryDetailsRoot(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RepositoryDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Use LaunchedEffect to collect the one-time events
    LaunchedEffect(viewModel) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                RepositoryDetailsContract.Effect.NavigateBack -> navController.navigateUp()
            }
        }
    }

    RepositoryDetailsRoot(
        modifier = modifier,
        state = state,
        onBackClicked = {
            navController.navigateUp()
        },
        onRetryClicked = {
            viewModel.onIntent(RepositoryDetailsContract.Event.Retry)
        },
    )
}

@Composable
private fun RepositoryDetailsRoot(
    state: RepositoryDetailsContract.State,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onRetryClicked: () -> Unit,
) {
    val details = state.repositoryDetails
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = details.title) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPadding ->
        val contentModifier = Modifier
            .padding(innerPadding)

        when (state.contentState) {
            is ContentState.Error -> Error(
                model = state.contentState as ContentState.Error,
                modifier = contentModifier,
                onRetryClicked = onRetryClicked,
            )

            ContentState.Progress -> Progress()
            ContentState.Success -> {
                Content(
                    details = details,
                    modifier = contentModifier,
                )
            }
        }
    }
}

@Composable
private fun Content(
    details: UiRepositoryDetails,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        SectionHeader(details)

        Spacer(Modifier.height(16.dp))

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OpenIssuesSection(
                openIssues = details.openIssues,
                openIssuesCount = details.openIssuesCount,
                modifier = Modifier.padding(vertical = 8.dp),
            )
            OpenPullRequestsSection(
                openPullRequests = details.openPullRequests,
                openPullRequestsCount = details.openPullRequestsCount,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
    }
}

@Composable
private fun OpenPullRequestsSection(
    openPullRequests: List<UiPullRequest>,
    openPullRequestsCount: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Open Pull Requests ($openPullRequestsCount):",
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
    )

    if (openPullRequests.isNotEmpty()) {
        openPullRequests.forEach { pr ->
            Text(
                text = "• ${pr.title}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp),
            )
        }
    } else {
        Text(
            text = "No open pull requests",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 4.dp),
        )
    }
}

@Composable
private fun OpenIssuesSection(
    openIssues: List<UiIssue>,
    openIssuesCount: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Open Issues ($openIssuesCount):",
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
    )

    if (openIssues.isNotEmpty()) {
        openIssues.forEach { issue ->
            Text(
                text = "• ${issue.title}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp),
            )
        }
    } else {
        Text(
            text = "No open issues",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 4.dp),
        )
    }
}

@Composable
private fun SectionHeader(details: UiRepositoryDetails, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        with(details) {
            Text(
                text = "Url: $url",
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = "Issues: Open: $openIssuesCount, Closed: $closedIssuesCount",
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = "Pull Requests: Open: $openPullRequestsCount, Closed: $closedPullRequestsCount",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun Error(
    model: ContentState.Error,
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(text = model.cause.asText())
            Spacer(Modifier.height(16.dp))
            Button(onClick = onRetryClicked) {
                Text(text = "Retry")
            }
        }
    }
}

private fun DomainError.asText(): String {
    return when (this) {
        GeneralError.Network.NoConnection -> "No Internet connection"
        is GeneralError.Unknown -> message ?: "Unknown error"
        else -> "Unknown error"
    }
}

@Composable
private fun Progress(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun RepositoryDetailsErrorPreview() {
    ToptalTheme {
        RepositoryDetailsRoot(
            state = RepositoryDetailsContract.State(
                UiRepositoryDetails.EMPTY.copy(title = "Fixture Repository Name"),
                contentState = ContentState.Error(cause = GeneralError.Network.NoConnection),
            ),
            onBackClicked = { },
            onRetryClicked = { },
        )
    }
}

@Preview
@Composable
private fun RepositoryDetailsPreview() {
    ToptalTheme {
        RepositoryDetailsRoot(
            state = RepositoryDetailsContract.State(
                UiRepositoryDetails.EMPTY.copy(title = "Fixture Repository Name", url = "https://www.example.com"),
                contentState = ContentState.Success,
            ),
            onBackClicked = { },
            onRetryClicked = { },
        )
    }
}

