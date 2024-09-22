package com.toptal.github.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.toptal.github.presentation.repository.details.RepositoryDetailsRoot
import com.toptal.github.presentation.repository.list.RepositoriesListRoot
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.RepositoriesList) {
        composable<Navigation.RepositoriesList> {
            RepositoriesListRoot(navController)
        }
        composable<Navigation.RepositoryDetails> {
            RepositoryDetailsRoot(navController)
        }
    }
}

object Navigation {

    @Serializable
    object RepositoriesList

    @Serializable
    data class RepositoryDetails(val name: String, val owner: String = "toptal") {

        companion object {

            fun from(savedStateHandle: SavedStateHandle) = savedStateHandle.toRoute<RepositoryDetails>()
        }
    }
}
