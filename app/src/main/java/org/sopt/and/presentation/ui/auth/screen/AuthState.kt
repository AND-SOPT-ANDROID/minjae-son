package org.sopt.and.presentation.ui.auth.screen

import org.sopt.and.data.remote.model.response.ResponseUserRegistrationDto

sealed class SignInState {
    data object Idle : SignInState()
    data object EmailEmpty : SignInState()
    data object PasswordEmpty : SignInState()
    data object Success : SignInState()
    data class Failure(val errorMessage: String) : SignInState()
}

sealed class SignUpState {
    data object Idle : SignUpState()
    data class Success(val response: ResponseUserRegistrationDto?) : SignUpState()
    data class Failure(val errorMessage: String) : SignUpState()
}
