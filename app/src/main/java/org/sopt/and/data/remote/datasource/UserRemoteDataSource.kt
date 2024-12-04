package org.sopt.and.data.remote.datasource

import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.UserInfoUpdateRequestDto
import org.sopt.and.data.remote.model.response.HobbyResponseDto
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun getMyHobby(token: String): ApiResponse<HobbyResponseDto>
    suspend fun getOthersHobby(token: String, userNo: Int): ApiResponse<HobbyResponseDto>
    suspend fun updateUserInfo(token: String, userInfoUpdateRequestDto: UserInfoUpdateRequestDto): Response<Unit>
}