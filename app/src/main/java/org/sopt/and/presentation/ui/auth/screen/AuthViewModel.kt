package org.sopt.and.presentation.ui.auth.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.TokenRepository
import org.sopt.and.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository,
) : BaseViewModel<AuthContract.AuthUiState, AuthContract.AuthSideEffect, AuthContract.AuthEvent>() {
    override fun createInitialState(): AuthContract.AuthUiState = AuthContract.AuthUiState()

    override suspend fun handleEvent(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.OnSignInClicked -> setState {
                copy(
                    signInState = event.signInState,
                )
            }
            is AuthContract.AuthEvent.OnSignUpClicked -> setState {
                copy(
                    signUpState = event.signUpState,
                )
            }
        }
    }

    fun validateSignIn() {
        viewModelScope.launch {
            setEvent(AuthContract.AuthEvent.OnSignInClicked(signInState = SignInState.Loading))
            authRepository.login(username = currentState.signInUsername, password = currentState.signInPassword).fold(
                onSuccess = { token ->
                    tokenRepository.setToken(token.token)
                    setEvent(AuthContract.AuthEvent.OnSignInClicked(signInState = SignInState.Success(result = token)))
                },
                onFailure = {
                    setEvent(AuthContract.AuthEvent.OnSignInClicked(signInState = SignInState.Failure(errorMessage = it.localizedMessage?: "")))
                }
            )
        }
    }

    fun validateSignUp() {
        viewModelScope.launch {
            setEvent(AuthContract.AuthEvent.OnSignUpClicked(signUpState = SignUpState.Loading))
            authRepository.registerUser(
                user = User(
                    username = currentState.signUpUsername,
                    password = currentState.signUpPassword,
                    hobby = currentState.signUpHobby
                )
            ).fold(
                onSuccess = { userNo ->
                    setEvent(AuthContract.AuthEvent.OnSignUpClicked(signUpState = SignUpState.Success(userNo)))
                },
                onFailure = {
                    setEvent(AuthContract.AuthEvent.OnSignUpClicked(signUpState = SignUpState.Failure(it.localizedMessage ?: "")))
                }
            )
        }
    }

//    fun resetSignInState() {
//        _signInState.value = SignInState.Idle
//    }
//
//    fun resetSignUpState() {
//        _signUpState.value = SignUpState.Idle
//    }
}