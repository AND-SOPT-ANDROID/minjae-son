package org.sopt.and.data.remote.datasourceimpl

import org.sopt.and.data.remote.datasource.UserRemoteDataSource
import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.UserInfoUpdateRequestDto
import org.sopt.and.data.remote.model.response.HobbyResponseDto
import org.sopt.and.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
): UserRemoteDataSource {
    override suspend fun getMyHobby(token: String): ApiResponse<HobbyResponseDto> =
        userService.getMyHobby(token = token)


    override suspend fun getOthersHobby(token: String, userNo: Int): ApiResponse<HobbyResponseDto> =
        userService.getOthersHobby(token = token, userNo = userNo)

    override suspend fun updateUserInfo(
        token: String,
        userInfoUpdateRequestDto: UserInfoUpdateRequestDto
    ): ApiResponse<Unit> =
        userService.updateUserInfo(token = token, userInfoUpdateRequestDto = userInfoUpdateRequestDto)
}