package org.sopt.and.presentation.ui.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable
import org.sopt.and.presentation.ui.auth.screen.AuthViewModel
import org.sopt.and.presentation.ui.auth.screen.SignInRoute
import org.sopt.and.presentation.ui.auth.screen.SignUpRoute
import org.sopt.and.presentation.ui.navigation.WavveRoute

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
) {
    navigation(
        startDestination = WavveRoute.SIGN_IN,
        route = WavveRoute.AUTH,
    ) {
        composable(route = WavveRoute.SIGN_IN) {
            SignInRoute(
                navController = navController,
                authViewModel = authViewModel,
            )
        }

        composable(route = WavveRoute.SIGN_UP) {
            SignUpRoute(
                navController = navController,
                authViewModel = authViewModel,
            )
        }
    }
}