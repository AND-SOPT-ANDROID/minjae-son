package org.sopt.and.presentation.ui.auth.screen

import org.sopt.and.domain.model.Token
import org.sopt.and.domain.model.UserNo
import org.sopt.and.presentation.util.base.UiEvent
import org.sopt.and.presentation.util.base.UiSideEffect
import org.sopt.and.presentation.util.base.UiState

class AuthContract {
    data class AuthUiState(
        val signInState: SignInState = SignInState.Idle,
        val signInUsername: String = "",
        val signInPassword: String = "",
        val signUpState: SignUpState = SignUpState.Idle,
        val signUpUsername: String = "",
        val signUpPassword: String = "",
        val signUpHobby: String = "",
    ): UiState

    sealed interface AuthSideEffect: UiSideEffect {
        data object NavigateToSignIn: AuthSideEffect
        data object NavigateToSignUp: AuthSideEffect
        data object NavigateToMain: AuthSideEffect
    }

    sealed class AuthEvent: UiEvent {
        data class OnSignInClicked(val signInState: SignInState): AuthEvent()
        data class OnSignUpClicked(val signUpState: SignUpState): AuthEvent()
    }
}