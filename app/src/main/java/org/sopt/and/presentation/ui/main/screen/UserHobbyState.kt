package org.sopt.and.presentation.ui.main.screen

sealed class UserHobbyState {
    data object Idle: UserHobbyState()
    data class Success(val hobby: String): UserHobbyState()
    data class Failure(val errorMessage: String): UserHobbyState()
}