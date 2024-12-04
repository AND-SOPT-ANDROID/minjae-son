package org.sopt.and.presentation.ui.auth.screen

import org.sopt.and.domain.model.Token
import org.sopt.and.domain.model.UserNo

sealed class SignInState {
    data object Idle : SignInState()
    data object Loading : SignInState()
    data class Success(val result: Token) : SignInState()
    data class Failure(val errorMessage: String) : SignInState()
}

sealed class SignUpState {
    data object Idle : SignUpState()
    data object Loading : SignUpState()
    data class Success(val result: UserNo) : SignUpState()
    data class Failure(val errorMessage: String) : SignUpState()
}
