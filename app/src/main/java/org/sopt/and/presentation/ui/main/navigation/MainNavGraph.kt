package org.sopt.and.presentation.ui.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.sopt.and.presentation.ui.main.screen.HomeScreen
import org.sopt.and.presentation.ui.main.screen.MainRoute
import org.sopt.and.presentation.ui.main.screen.MyPageScreen
import org.sopt.and.presentation.ui.main.screen.SearchScreen
import org.sopt.and.presentation.ui.navigation.WavveRoute

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = WavveRoute.MAIN,
        route = WavveRoute.WAVVE
    ) {
        composable(route = WavveRoute.MAIN) {
            MainRoute(
                navigateToHome = { navController.navigateToHome() },
                navigateToSearch = { navController.navigateToSearch() },
                navigateToMy = { navController.navigateToMy() }
            )
        }
        composable(route = WavveRoute.HOME) {
            HomeScreen()
        }
        composable(route = WavveRoute.SEARCH) {
            SearchScreen()
        }
        composable(route = WavveRoute.MY) {
            MyPageScreen(userHobby = "")
        }
    }
}