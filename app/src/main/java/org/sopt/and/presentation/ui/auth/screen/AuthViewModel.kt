package org.sopt.and.presentation.ui.auth.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val _authEmail = MutableLiveData("")
    val authEmail: LiveData<String> = _authEmail

    private val _authPassword = MutableLiveData("")
    val authPassword: LiveData<String> = _authPassword

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState : StateFlow<SignInState> = _signInState

    fun setAuthInfo(
        authEmail: String,
        authPassword: String,
    ) {
        _authEmail.value = authEmail
        _authPassword.value = authPassword
    }

    fun isSignInValid(
        inputEmail: String,
        inputPassword: String
    ): SignInState {
//        return when {
//            inputEmail.isEmpty() -> SignInState.EmailEmpty
//            inputPassword.isEmpty() -> SignInState.PasswordEmpty
//            _authEmail.value != inputEmail -> SignInState.EmailInvalid
//            _authPassword.value != inputPassword -> SignInState.PasswordInvalid
//            else -> SignInState.Success
//        }
        return SignInState.Success
    }

    fun updateSignInState(newState: SignInState) {
        _signInState.value = newState
    }

    private fun isSignUpEmailValid(email: String): Boolean {
        return email.matches(EMAIL_VALIDATION_REGEX)
    }

    private fun isSignUpPasswordValid(password: String): Boolean {
        val count = listOf(
            UPPER_CASE_REGEX.containsMatchIn(password),
            LOWER_CASE_REGEX.containsMatchIn(password),
            DIGIT_REGEX.containsMatchIn(password),
            SPECIAL_CHAR_REGEX.containsMatchIn(password)
        ).count { it }

        return password.length in PW_MIN_LENGTH..PW_MAX_LENGTH && count >= PW_MIN_TYPE_COUNT
    }

    fun isSignUpValid(email: String, password: String): Boolean {
        return isSignUpEmailValid(email) && isSignUpPasswordValid(password)
    }

    companion object {
        private const val PW_MIN_LENGTH = 8
        private const val PW_MAX_LENGTH = 20
        private const val PW_MIN_TYPE_COUNT = 3

        private val EMAIL_VALIDATION_REGEX = "^[a-zA-Z0   -9]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}\$".toRegex()
        private val UPPER_CASE_REGEX = "[A-Z]".toRegex()
        private val LOWER_CASE_REGEX = "[a-z]".toRegex()
        private val DIGIT_REGEX = "[0-9]".toRegex()
        private val SPECIAL_CHAR_REGEX = "[!@#\$%^&*(),.?\":{}|<>]".toRegex()
    }
}