package org.sopt.and.data.remote.service

import org.sopt.and.data.remote.model.request.LoginRequestDto
import org.sopt.and.data.remote.model.request.UserRegistrationRequestDto
import org.sopt.and.data.remote.model.response.LoginResponseDto
import org.sopt.and.data.remote.model.response.UserRegistrationResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user")
    fun registerUser(
        @Body userRegistrationRequestDto: UserRegistrationRequestDto
    ): Call<UserRegistrationResponseDto>

    @POST("login")
    fun login(
        @Body loginRequestDto: LoginRequestDto
    ): Call<LoginResponseDto>
}