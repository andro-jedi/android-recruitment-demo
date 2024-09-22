package com.toptal.github.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.toptal.design.ToptalTheme
import com.toptal.github.presentation.repository.list.RepositoriesListRoot

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            ToptalTheme {
                RepositoriesListRoot()
            }
        }
    }
}
