package org.sopt.and.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.sopt.and.presentation.ui.auth.navigation.authNavGraph
import org.sopt.and.presentation.ui.auth.screen.AuthViewModel
import org.sopt.and.presentation.ui.main.navigation.mainNavGraph
import org.sopt.and.presentation.ui.main.screen.MainViewModel

@Composable
fun WavveNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val mainViewModel: MainViewModel = hiltViewModel()
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = WavveRoute.AUTH,
        ) {
            authNavGraph(navController, authViewModel)
            mainNavGraph(navController, mainViewModel)
        }
    }
}