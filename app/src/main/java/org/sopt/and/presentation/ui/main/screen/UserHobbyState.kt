package org.sopt.and.presentation.ui.main.screen

import org.sopt.and.domain.model.Hobby

sealed class UserHobbyState {
    data object Idle: UserHobbyState()
    data object Loading: UserHobbyState()
    data class Success(val result: Hobby): UserHobbyState()
    data class Failure(val errorMessage: String): UserHobbyState()
}