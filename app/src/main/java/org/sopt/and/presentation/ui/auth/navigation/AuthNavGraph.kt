package org.sopt.and.presentation.ui.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.sopt.and.presentation.ui.auth.screen.SignInRoute
import org.sopt.and.presentation.ui.auth.screen.SignUpRoute
import org.sopt.and.presentation.ui.main.navigation.navigateToMain
import org.sopt.and.presentation.ui.navigation.WavveRoute

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = WavveRoute.SIGN_IN,
        route = WavveRoute.AUTH,
    ) {
        composable(route = WavveRoute.SIGN_IN) {
            SignInRoute(
                navigateToSignUp = { navController.navigateToSignUp() },
                navigateToMain = { navController.navigateToMain() },
            )
        }

        composable(route = WavveRoute.SIGN_UP) {
            SignUpRoute(
                navigateToSignIn = { navController.navigateToSignIn() },
                navigateToBack = { navController.popBackStack() }
            )
        }
    }
}