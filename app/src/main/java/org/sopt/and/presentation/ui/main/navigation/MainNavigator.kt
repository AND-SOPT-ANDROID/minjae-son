package org.sopt.and.presentation.ui.main.navigation

import androidx.navigation.NavController
import org.sopt.and.presentation.ui.navigation.WavveRoute

fun NavController.navigateToMain() {
    navigate(WavveRoute.MAIN)
}

fun NavController.navigateToHome() {
    navigate(WavveRoute.HOME)
}

fun NavController.navigateToSearch() {
    navigate(WavveRoute.SEARCH)
}

fun NavController.navigateToMy() {
    navigate(WavveRoute.MY)
}