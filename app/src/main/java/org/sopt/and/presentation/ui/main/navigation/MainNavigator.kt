package org.sopt.and.presentation.ui.main.navigation

import androidx.navigation.NavController
import org.sopt.and.presentation.ui.navigation.WavveRoute

fun NavController.navigateToMain(
    userEmail: String,
) {
    navigate("${WavveRoute.MAIN}/$userEmail")
}