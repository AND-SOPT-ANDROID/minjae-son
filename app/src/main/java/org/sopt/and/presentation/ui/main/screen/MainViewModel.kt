package org.sopt.and.presentation.ui.main.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.TokenRepository
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val token = tokenRepository.getToken()

    private val _userHobbyState = MutableStateFlow<UserHobbyState>(UserHobbyState.Idle)
    val userHobbyState: StateFlow<UserHobbyState> = _userHobbyState

    private val _hobbySearchState = MutableStateFlow<HobbySearchState>(HobbySearchState.Idle)
    val hobbySearchState: StateFlow<HobbySearchState> = _hobbySearchState

    private val _userInfoUpdateState =
        MutableStateFlow<UserInfoUpdateState>(UserInfoUpdateState.Idle)
    val userInfoUpdateState: StateFlow<UserInfoUpdateState> = _userInfoUpdateState

    fun getUserHobby() {
        viewModelScope.launch {
            _userHobbyState.value = UserHobbyState.Loading
            val result = userRepository.getMyHobby(token = token)
            _userHobbyState.value = result.fold(
                onSuccess = {
                    UserHobbyState.Success(it)
                },
                onFailure = {
                    UserHobbyState.Failure(it.localizedMessage ?: "에러 발생")
                }
            )
        }
    }

    fun getOthersHobby(userNo: String) {
        viewModelScope.launch {
            _hobbySearchState.value = HobbySearchState.Loading
            val result = userRepository.getOthersHobby(
                token = token,
                userNo = userNo.toInt()
            )
            _hobbySearchState.value = result.fold(
                onSuccess = {
                    HobbySearchState.Success(it)
                },
                onFailure = {
                    HobbySearchState.Failure(it.localizedMessage ?: "에러 발생")
                }
            )
        }
    }

    fun updateUserInfo(password: String?, hobby: String?) {
        viewModelScope.launch {
            _userInfoUpdateState.value = UserInfoUpdateState.Loading
            val result = userRepository.updateUserInfo(
                token = token,
                password = password,
                hobby = hobby
            )
            _userInfoUpdateState.value = result.fold(
                onSuccess = {
                    UserInfoUpdateState.Success
                },
                onFailure = {
                    UserInfoUpdateState.Failure(it.localizedMessage ?: "에러발생")
                }
            )
        }
    }

    fun resetUserInfoUpdateState() {
        _userInfoUpdateState.value = UserInfoUpdateState.Idle
    }
}