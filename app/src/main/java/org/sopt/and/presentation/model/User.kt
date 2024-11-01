package org.sopt.and.presentation.model

import org.sopt.and.presentation.ui.auth.screen.SignUpState

data class User(
    val email: String,
    val password: String,
) {
    companion object {
        val EMAIL_VALIDATION_REGEX = "^[a-zA-Z0   -9]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}\$".toRegex()

        const val PW_MIN_LENGTH = 8
        const val PW_MAX_LENGTH = 20
        const val PW_MIN_TYPE_COUNT = 3
        val UPPER_CASE_REGEX = "[A-Z]".toRegex()
        val LOWER_CASE_REGEX = "[a-z]".toRegex()
        val DIGIT_REGEX = "[0-9]".toRegex()
        val SPECIAL_CHAR_REGEX = "[!@#\$%^&*(),.?\":{}|<>]".toRegex()
    }
}

fun User.validateSignUp(): SignUpState {
    return when {
        !isSignUpEmailValidate() -> SignUpState.EmailInvalid
        !isSignUpPasswordValidate() -> SignUpState.PasswordInvalid
        else -> SignUpState.Success
    }
}

fun User.isSignUpEmailValidate(): Boolean {
    return email.matches(User.EMAIL_VALIDATION_REGEX)
}

fun User.isSignUpPasswordValidate(): Boolean {
    val count = listOf(
        User.UPPER_CASE_REGEX.containsMatchIn(password),
        User.LOWER_CASE_REGEX.containsMatchIn(password),
        User.DIGIT_REGEX.containsMatchIn(password),
        User.SPECIAL_CHAR_REGEX.containsMatchIn(password)
    ).count { it }

    return password.length in User.PW_MIN_LENGTH..User.PW_MAX_LENGTH && count >= User.PW_MIN_TYPE_COUNT
}
