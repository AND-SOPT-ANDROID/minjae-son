package org.sopt.and.presentation.ui.auth.navigation

import androidx.navigation.NavController
import org.sopt.and.presentation.ui.navigation.WavveRoute

fun NavController.navigateToSignIn() {
    navigate(WavveRoute.SIGN_IN)
}

fun NavController.navigateToSignUp() {
    navigate(WavveRoute.SIGN_UP)
}