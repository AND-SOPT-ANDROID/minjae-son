package org.sopt.and.presentation.ui.auth.screen

sealed class SignInState {
    data object Idle : SignInState()
    data object EmailEmpty : SignInState()
    data object PasswordEmpty : SignInState()
    data object EmailInvalid : SignInState()
    data object PasswordInvalid : SignInState()
    data object Success : SignInState()
}

sealed class SignUpState {
    data object Idle: SignUpState()
    data object EmailInvalid: SignUpState()
    data object PasswordInvalid: SignUpState()
    data object Success: SignUpState()
}
