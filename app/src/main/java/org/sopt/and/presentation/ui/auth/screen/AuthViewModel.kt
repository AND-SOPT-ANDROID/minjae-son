package org.sopt.and.presentation.ui.auth.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.TokenRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState: StateFlow<SignInState> = _signInState

    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun validateSignIn(username: String, password: String) {
        viewModelScope.launch {
            _signInState.value = SignInState.Loading
            val result = authRepository.login(username = username, password = password)
            _signInState.value = result.fold(
                onSuccess = { token ->
                    tokenRepository.setToken(token.token)
                    SignInState.Success(token)
                },
                onFailure = {
                    SignInState.Failure(it.localizedMessage ?: "에러 발생")
                }
            )
        }
    }

    fun validateSignUp(username: String, password: String, hobby: String) {
        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            val result = authRepository.registerUser(
                user = User(
                    username = username,
                    password = password,
                    hobby = hobby
                )
            )
            _signUpState.value = result.fold(
                onSuccess = {
                    SignUpState.Success(it)
                },
                onFailure = {
                    SignUpState.Failure(it.localizedMessage ?: "에러 발생")
                }
            )
        }
    }

    fun resetSignInState() {
        _signInState.value = SignInState.Idle
    }

    fun resetSignUpState() {
        _signUpState.value = SignUpState.Idle
    }
}