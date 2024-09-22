package com.toptal.github.presentation.repository.details

import androidx.lifecycle.ViewModel
import com.toptal.github.presentation.details.UiRepositoryDetails
import kotlinx.coroutines.flow.MutableStateFlow

class RepositoryDetailsViewModel : ViewModel() {

    val state = MutableStateFlow<UiRepositoryDetails?>(value = null)
}
