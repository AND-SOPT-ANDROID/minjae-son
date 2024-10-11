package org.sopt.and.presentation.ui.auth

sealed class SignInState {
    data object Idle : SignInState()
    data object EmailEmpty : SignInState()
    data object PasswordEmpty : SignInState()
    data object EmailInvalid : SignInState()
    data object PasswordInvalid : SignInState()
    data object Success : SignInState()
}
