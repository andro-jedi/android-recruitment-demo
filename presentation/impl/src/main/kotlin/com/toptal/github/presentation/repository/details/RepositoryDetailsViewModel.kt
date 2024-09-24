package com.toptal.github.presentation.repository.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptal.core.common.DispatchersProvider
import com.toptal.domain.usecase.GetRepositoryDetailsUsecase
import com.toptal.github.presentation.navigation.Navigation
import com.toptal.github.presentation.repository.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRepositoryDetailsUsecase: GetRepositoryDetailsUsecase,
    private val dispatcherProvider: DispatchersProvider,
) : ViewModel() {

    private val args: Navigation.RepositoryDetails = Navigation.RepositoryDetails.from(savedStateHandle)

    private val _uiState = MutableStateFlow(
        RepositoryDetailsContract.State(
            // init title with args, other data can be added to args to provide initial screen state
            repositoryDetails = UiRepositoryDetails.EMPTY.copy(title = args.name),
        ),
    )
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<RepositoryDetailsContract.Effect>(Channel.BUFFERED)
    val uiEffect: Flow<RepositoryDetailsContract.Effect> = _uiEffect.receiveAsFlow()

    init {
        fetchDetails()
    }

    private fun fetchDetails() {
        viewModelScope.launch(dispatcherProvider.io) {
            _uiState.update { it.copy(contentState = ContentState.Progress) }
            getRepositoryDetailsUsecase(owner = args.owner, name = args.name)
                .onSuccess { item ->
                    _uiState.update { it.copy(repositoryDetails = item.toUi(), contentState = ContentState.Success) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(contentState = ContentState.Error(error)) }
                }
        }
    }

    fun onIntent(event: RepositoryDetailsContract.Event) {
        when (event) {
            RepositoryDetailsContract.Event.Retry -> fetchDetails()
        }
    }
}
