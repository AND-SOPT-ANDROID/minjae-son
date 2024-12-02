package org.sopt.and.data.remote.service

import org.sopt.and.data.remote.model.request.RequestLoginDto
import org.sopt.and.data.remote.model.request.RequestUserRegistrationDto
import org.sopt.and.data.remote.model.response.ResponseLoginDto
import org.sopt.and.data.remote.model.response.ResponseUserRegistrationDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user")
    fun registerUser(
        @Body requestUserRegistrationDto: RequestUserRegistrationDto
    ): Call<ResponseUserRegistrationDto>

    @POST("login")
    fun login(
        @Body requestLoginDto: RequestLoginDto
    ): Call<ResponseLoginDto>
}