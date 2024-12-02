package org.sopt.and.presentation.ui.auth.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.remote.model.request.LoginRequestDto
import org.sopt.and.data.remote.model.request.UserRegistrationRequestDto
import org.sopt.and.data.remote.model.response.LoginResponseDto
import org.sopt.and.data.remote.model.response.UserRegistrationResponseDto
import org.sopt.and.domain.repository.TokenRepository
import org.sopt.and.di.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
) : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState: StateFlow<SignInState> = _signInState

    private val _signInUserName = MutableLiveData("")
    val signInUserName: LiveData<String> = _signInUserName

    private val _signInPassword = MutableLiveData("")
    val signInPassword: LiveData<String> = _signInPassword

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState

    private val _signUpUserName = MutableLiveData("")
    val signUpUserName: LiveData<String> = _signUpUserName

    private val _signUpPassword = MutableLiveData("")
    val signUpPassword: LiveData<String> = _signUpPassword

    private val _signUpHobby = MutableLiveData("")
    val signUpHobby: LiveData<String> = _signUpHobby



    fun validateSignIn(inputEmail: String, inputPassword: String) {
        viewModelScope.launch {
            authService.login(LoginRequestDto(inputEmail, inputPassword)).enqueue(object :
                Callback<LoginResponseDto> {
                override fun onResponse(
                    call: Call<LoginResponseDto>,
                    response: Response<LoginResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _signInState.value = SignInState.Success
                        tokenRepository.setToken(token = response.body()!!.token)
                        Log.d("token", tokenRepository.getToken())
                    } else {
                        _signInState.value = SignInState.Failure(response.message())
                    }
                }

                override fun onFailure(
                    call: Call<LoginResponseDto>,
                    t: Throwable
                ) {
                    _signInState.value = SignInState.Failure(t.message.toString())
                }
            }
            )
        }
    }

    fun validateSignUp(email: String, password: String, hobby: String) {
        viewModelScope.launch {
            authService.registerUser(UserRegistrationRequestDto(email, password, hobby))
                .enqueue(object :
                    Callback<UserRegistrationResponseDto> {
                    override fun onResponse(
                        call: Call<UserRegistrationResponseDto>,
                        response: Response<UserRegistrationResponseDto>
                    ) {
                        if (response.isSuccessful) {
                            _signUpState.value = SignUpState.Success(response.body())
                        } else {
                            _signUpState.value = SignUpState.Failure(response.message())
                        }
                    }

                    override fun onFailure(
                        call: Call<UserRegistrationResponseDto>,
                        t: Throwable
                    ) {
                        _signUpState.value = SignUpState.Failure(t.message.toString())
                    }
                }
                )
        }
    }
}