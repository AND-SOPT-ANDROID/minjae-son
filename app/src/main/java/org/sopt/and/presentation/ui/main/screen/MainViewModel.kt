package org.sopt.and.presentation.ui.main.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.local.datasource.TokenLocalDataSource
import org.sopt.and.data.remote.model.response.HobbyResponseDto
import org.sopt.and.di.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource
) : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _userHobbyState = MutableStateFlow<UserHobbyState>(UserHobbyState.Idle)
    val userHobbyState: StateFlow<UserHobbyState> = _userHobbyState

    fun getUserHobby() {
        viewModelScope.launch {
            userService.getMyHobby(token = tokenLocalDataSource.token).enqueue(object :
                Callback<HobbyResponseDto> {
                override fun onResponse(
                    call: Call<HobbyResponseDto>,
                    response: Response<HobbyResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _userHobbyState.value =
                            UserHobbyState.Success(response.body()?.result!!.hobby)
                    } else {
                        _userHobbyState.value = UserHobbyState.Failure(response.message())
                    }
                }

                override fun onFailure(
                    call: Call<HobbyResponseDto>,
                    t: Throwable
                ) {
                    _userHobbyState.value = UserHobbyState.Failure(t.message.toString())
                }
            }
            )
        }
    }
}