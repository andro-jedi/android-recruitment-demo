package com.toptal.github.presentation.repository.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptal.core.common.DispatchersProvider
import com.toptal.domain.exception.DomainError
import com.toptal.domain.exception.GeneralError
import com.toptal.domain.usecase.FetchRepositoriesUsecase
import com.toptal.domain.usecase.GetRepositoriesUsecase
import com.toptal.github.presentation.repository.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val getRepositoriesUsecase: GetRepositoriesUsecase,
    private val fetchRepositoriesUsecase: FetchRepositoriesUsecase,
    private val dispatcherProvider: DispatchersProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RepositoriesListContract.State())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<RepositoriesListContract.Effect>(Channel.BUFFERED)
    val uiEffect: Flow<RepositoriesListContract.Effect> = _uiEffect.receiveAsFlow()

    init {
        getRepositories()
    }

    private fun getRepositories() {
        viewModelScope.launch(dispatcherProvider.io) {
            _uiState.update { it.copy(contentState = ContentState.Progress) }

            launch {
                fetchRepositoriesUsecase()
                    .onSuccess { items ->
                        _uiState.update {
                            it.copy(
                                items = items.map { it.toUi() },
                                contentState = ContentState.Success,
                            )
                        }
                    }
                    // TODO refactor error handling, cache is not working properly when error occur during fetch
                    .onFailure { onError(it) }
            }

            getRepositoriesUsecase()
                .onEach { result ->
                    result
                        .onSuccess { items ->
                            _uiState.update { it.copy(items = items.map { it.toUi() }) }
                        }
                        .onFailure { onError(it) }
                }
                .catch { onError(GeneralError.Unknown(it.message)) }
                .launchIn(this)
        }
    }

    private fun onError(error: DomainError) {
        // we can also divide errors to different categories and handle them differently
        _uiState.update {
            it.copy(contentState = ContentState.Error(error))
        }
    }

    fun onIntent(event: RepositoriesListContract.Event) {
        when (event) {
            is RepositoriesListContract.Event.ItemClicked -> {
                viewModelScope.launch {
                    _uiEffect.send(RepositoriesListContract.Effect.NavigateToDetails(event.name))
                }
            }

            RepositoriesListContract.Event.Retry -> getRepositories()
        }
    }
}
