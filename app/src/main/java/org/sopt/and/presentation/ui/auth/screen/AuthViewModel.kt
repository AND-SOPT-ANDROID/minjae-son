package org.sopt.and.presentation.ui.auth.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.presentation.model.User
import org.sopt.and.presentation.model.validateSignUp
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState : StateFlow<SignInState> = _signInState

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState : StateFlow<SignUpState> = _signUpState

    fun validateSignIn(inputEmail: String, inputPassword: String) {
        _signInState.value = when {
            inputEmail.isEmpty() -> SignInState.EmailEmpty
            inputPassword.isEmpty() -> SignInState.PasswordEmpty
            _user.value?.email != inputEmail -> SignInState.EmailInvalid
            _user.value?.password != inputPassword -> SignInState.PasswordInvalid
            else -> SignInState.Success
        }
    }

    fun validateSignUp(email: String, password: String) {
        val authInfo = User(email, password)
        _signUpState.value = authInfo.validateSignUp()
        if (_signUpState.value is SignUpState.Success) _user.value = authInfo
    }
}