package org.sopt.and.presentation.utils

import org.sopt.and.presentation.ui.auth.SignInState

object AuthValidation {
    private const val PW_MIN_LENGTH = 8
    private const val PW_MAX_LENGTH = 20
    private const val PW_MIN_TYPE_COUNT = 3

    private val EMAIL_VALIDATION_REGEX = "^[a-zA-Z0   -9]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}\$".toRegex()
    private val UPPER_CASE_REGEX = "[A-Z]".toRegex()
    private val LOWER_CASE_REGEX = "[a-z]".toRegex()
    private val DIGIT_REGEX = "[0-9]".toRegex()
    private val SPECIAL_CHAR_REGEX = "[!@#\$%^&*(),.?\":{}|<>]".toRegex()

    fun isSignInValid(
        userEmail: String,
        userPassword: String,
        inputEmail: String,
        inputPassword: String
    ): SignInState {
        return when {
            inputEmail.isEmpty() -> SignInState.EmailEmpty
            inputPassword.isEmpty() -> SignInState.PasswordEmpty
            userEmail != inputEmail -> SignInState.EmailInvalid
            userPassword != inputPassword -> SignInState.PasswordInvalid
            else -> SignInState.Success
        }
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
}