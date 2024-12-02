package org.sopt.and.presentation.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.sopt.and.presentation.ui.auth.navigation.authNavGraph
import org.sopt.and.presentation.ui.main.navigation.mainNavGraph

@Composable
fun WavveNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = WavveRoute.AUTH,
        ) {
            authNavGraph(navController)
            mainNavGraph(navController)
        }
    }
}