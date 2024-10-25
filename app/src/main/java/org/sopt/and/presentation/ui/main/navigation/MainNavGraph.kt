package org.sopt.and.presentation.ui.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.and.presentation.ui.main.screen.MainRoute
import org.sopt.and.presentation.ui.navigation.KeyStorage
import org.sopt.and.presentation.ui.navigation.WavveRoute

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {
    composable(
        route = "${WavveRoute.MAIN}/{${KeyStorage.USER_EMAIL}}",
        arguments = listOf(
            navArgument(KeyStorage.USER_EMAIL) { type = NavType.StringType }
        )
    ) { navBackStackEntry ->
        val userEmail =
            navBackStackEntry.arguments?.getString(KeyStorage.USER_EMAIL) ?: "unknown@example.com"
        MainRoute(
            navController = navController,
            userEmail = userEmail,
        )
    }
}
