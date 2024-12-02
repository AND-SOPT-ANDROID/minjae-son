package org.sopt.and.presentation.ui.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.sopt.and.presentation.ui.main.screen.HomeRoute
import org.sopt.and.presentation.ui.main.screen.MainRoute
import org.sopt.and.presentation.ui.main.screen.MyPageRoute
import org.sopt.and.presentation.ui.main.screen.SearchRoute
import org.sopt.and.presentation.ui.main.screen.SettingRoute
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
                navigateToSetting = { navController.navigateToSetting() }
            )
        }
        composable(route = WavveRoute.HOME) {
            HomeRoute()
        }
        composable(route = WavveRoute.SEARCH) {
            SearchRoute()
        }
        composable(route = WavveRoute.MY) {
            MyPageRoute(
                navigateToSetting = { navController.navigateToSetting() }
            )
        }
        composable(route = WavveRoute.SETTING) {
            SettingRoute()
        }
    }
}