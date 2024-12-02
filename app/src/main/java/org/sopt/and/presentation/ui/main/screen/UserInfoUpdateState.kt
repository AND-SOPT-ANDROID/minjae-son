package org.sopt.and.presentation.ui.main.screen

import org.sopt.and.domain.model.Hobby

sealed class UserInfoUpdateState {
    data object Idle: UserInfoUpdateState()
    data object Loading: UserInfoUpdateState()
    data object Success: UserInfoUpdateState()
    data class Failure(val errorMessage: String): UserInfoUpdateState()
}