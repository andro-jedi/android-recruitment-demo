package com.toptal.github.presentation.repository.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import com.toptal.domain.exception.DataError
import com.toptal.domain.exception.DomainError
import com.toptal.github.presentation.navigation.Navigation

@Composable
fun RepositoriesListRoot(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RepositoriesListViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Use LaunchedEffect to collect the one-time events
    LaunchedEffect(viewModel) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is RepositoriesListContract.Effect.NavigateToDetails -> {
                    navController.navigate(Navigation.RepositoryDetails(effect.name))
                }
            }
        }
    }

    RepositoriesListRoot(
        modifier = modifier,
        state = state,
        onItemClicked = { id ->
            viewModel.onIntent(RepositoriesListContract.Event.ItemClicked(id))
        },
        onRetryClicked = {
            viewModel.onIntent(RepositoriesListContract.Event.Retry)
        },
    )
}

@Composable
private fun RepositoriesListRoot(
    modifier: Modifier = Modifier,
    state: RepositoriesListContract.State,
    onItemClicked: (id: String) -> Unit,
    onRetryClicked: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Repositories") },
            )
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPadding ->
        when (state.contentState) {
            is ContentState.Error -> Error(
                model = state.contentState as ContentState.Error,
                onRetryClicked = onRetryClicked,
            )

            ContentState.Progress -> Progress()
            ContentState.Success -> {
                LazyColumn(
                    modifier = Modifier.consumeWindowInsets(innerPadding),
                    contentPadding = innerPadding,
                ) {
                    items(state.items, key = { it.id }) {
                        List(
                            item = it,
                            onItemClicked = onItemClicked,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun List(
    item: UiRepositoryItem,
    modifier: Modifier = Modifier,
    onItemClicked: (id: String) -> Unit,
) {
    Card(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = { onItemClicked(item.name) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(text = item.url)
        }
    }
}

@Composable
private fun Error(
    model: ContentState.Error,
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = model.cause.asText())

        Button(onClick = onRetryClicked) {
            Text(text = "Retry")
        }
    }
}

private fun DomainError.asText(): String {
    return when (this) {
        DataError.Network.NO_INTERNET -> "No Internet connection"
        else -> "Unknown error!"
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
private fun RepositoriesListPreview() {
    ToptalTheme {
        RepositoriesListRoot(
            state = RepositoriesListContract.State(
                contentState = ContentState.Success,
                items = buildList {
                    addAll(
                        List(5) {
                            UiRepositoryItem(
                                id = "$it",
                                name = "index=$it",
                                url = "fixture-url",
                            )
                        },
                    )
                },
            ),
            onItemClicked = {},
            onRetryClicked = {},
        )
    }
}
