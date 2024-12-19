package org.sopt.and.presentation.ui.auth.screen

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
    ) : UiState

    sealed class AuthEvent : UiEvent {
        data class UpdateSignInUsername(val signInUsername: String) : AuthEvent()
        data class UpdateSignInPassword(val signInPassword: String) : AuthEvent()
        data class OnSignInClicked(val signInState: SignInState) : AuthEvent()
        data object ResetSignInState : AuthEvent()
        data class UpdateSignUpUsername(val signUpUsername: String) : AuthEvent()
        data class UpdateSignUpPassword(val signUpPassword: String) : AuthEvent()
        data class UpdateSignUpHobby(val signUpHobby: String) : AuthEvent()
        data class OnSignUpClicked(val signUpState: SignUpState) : AuthEvent()
        data object ResetSignUpState : AuthEvent()
    }

    sealed interface AuthSideEffect : UiSideEffect {
        data object NavigateToSignIn : AuthSideEffect
        data object NavigateToSignUp : AuthSideEffect
        data object NavigateToMain : AuthSideEffect
        data class ShowToast(val message: String) : AuthSideEffect
    }
}