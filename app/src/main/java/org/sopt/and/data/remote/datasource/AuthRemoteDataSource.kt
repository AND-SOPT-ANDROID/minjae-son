package org.sopt.and.data.remote.datasource

import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.LoginRequestDto
import org.sopt.and.data.remote.model.request.UserRegistrationRequestDto
import org.sopt.and.data.remote.model.response.LoginResponseDto
import org.sopt.and.data.remote.model.response.UserRegistrationResponseDto

interface AuthRemoteDataSource {
    suspend fun registerUser(userRegistrationRequestDto: UserRegistrationRequestDto): ApiResponse<UserRegistrationResponseDto>
    suspend fun login(loginRequestDto: LoginRequestDto): ApiResponse<LoginResponseDto>
}