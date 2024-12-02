package org.sopt.and.data.remote.service

import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.UserInfoUpdateRequestDto
import org.sopt.and.data.remote.model.response.HobbyResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("user/my-hobby")
    fun getMyHobby(
        @Header("token") token: String
    ): ApiResponse<HobbyResponseDto>

    @GET("user/{no}/hobby")
    fun getOthersHobby(
        @Header("token") token: String,
        @Path("no") userNo: Int
    ): ApiResponse<HobbyResponseDto>

    @PUT("user")
    fun updateUserInfo(
        @Header("token") token: String,
        @Body userInfoUpdateRequestDto: UserInfoUpdateRequestDto
    ): ApiResponse<Unit>
}