package org.sopt.and.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.presentation.ui.auth.screen.AuthViewModel
import org.sopt.and.presentation.ui.navigation.WavveNavHost
import org.sopt.and.ui.theme.ANDANDROIDTheme

@AndroidEntryPoint
class WavveActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel by viewModels()
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    content = { paddingValues ->
                        WavveNavHost(
                            navController = navController,
                            authViewModel = authViewModel,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                )
            }
        }
    }
}
