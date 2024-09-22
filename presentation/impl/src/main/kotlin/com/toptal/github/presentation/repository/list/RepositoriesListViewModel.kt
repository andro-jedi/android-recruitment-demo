package com.toptal.github.presentation.repository.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptal.github.presentation.listing.UiRepositoryItem
import com.toptal.github.presentation.listing.UiRepositoryList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepositoriesListViewModel : ViewModel() {

    val list = MutableStateFlow<UiRepositoryList?>(value = null)

    init {
        viewModelScope.launch {
            list.update {
                UiRepositoryList(
                    items = List(1) {
                        UiRepositoryItem.Progress
                    },
                )
            }
            delay(2000)

            list.update {
                UiRepositoryList(
                    items = List(40) {
                        UiRepositoryItem.Repository(
                            name = "toptal/gitignore $it",
                            url = "https://github.com/toptal/gitignore $it",
                            onClicked = { },
                        )
                    },
                )
            }
        }
    }
}
