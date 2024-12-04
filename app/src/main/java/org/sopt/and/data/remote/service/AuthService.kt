package org.sopt.and.data.remote.service

import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.LoginRequestDto
import org.sopt.and.data.remote.model.request.UserRegistrationRequestDto
import org.sopt.and.data.remote.model.response.LoginResponseDto
import org.sopt.and.data.remote.model.response.UserRegistrationResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user")
    suspend fun registerUser(
        @Body userRegistrationRequestDto: UserRegistrationRequestDto
    ): ApiResponse<UserRegistrationResponseDto>

    @POST("login")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): ApiResponse<LoginResponseDto>
}