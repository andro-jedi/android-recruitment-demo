package com.toptal.github.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.toptal.design.ToptalTheme
import com.toptal.github.presentation.listing.RepositoriesListRoot

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = true

        setContent {
            ToptalTheme {
                RepositoriesListRoot()
            }
        }
    }
}
