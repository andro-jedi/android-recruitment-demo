package com.toptal.github.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toptal.github.presentation.repository.list.RepositoriesListRoot
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.RepositoriesList) {
        composable<Navigation.RepositoriesList> {
            RepositoriesListRoot()
        }
    }
}

object Navigation {

    @Serializable
    object RepositoriesList

    @Serializable
    data class RepositoryDetails(val id: String)
}
