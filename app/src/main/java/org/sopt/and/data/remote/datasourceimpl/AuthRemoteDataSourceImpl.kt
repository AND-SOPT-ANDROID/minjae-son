package org.sopt.and.data.remote.datasourceimpl

import org.sopt.and.data.remote.datasource.AuthRemoteDataSource
import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.LoginRequestDto
import org.sopt.and.data.remote.model.request.UserRegistrationRequestDto
import org.sopt.and.data.remote.model.response.LoginResponseDto
import org.sopt.and.data.remote.model.response.UserRegistrationResponseDto
import org.sopt.and.data.remote.service.AuthService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun registerUser(userRegistrationRequestDto: UserRegistrationRequestDto): ApiResponse<UserRegistrationResponseDto> =
        authService.registerUser(userRegistrationRequestDto = userRegistrationRequestDto)

    override suspend fun login(loginRequestDto: LoginRequestDto): ApiResponse<LoginResponseDto> =
        authService.login(loginRequestDto = loginRequestDto)
}