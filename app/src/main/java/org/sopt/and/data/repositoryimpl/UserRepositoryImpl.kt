package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.mapper.todomain.toDomain
import org.sopt.and.data.remote.datasource.UserRemoteDataSource
import org.sopt.and.data.remote.model.request.UserInfoUpdateRequestDto
import org.sopt.and.data.remote.util.handleApiResponse
import org.sopt.and.domain.model.Hobby
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun getMyHobby(token: String): Result<Hobby> {
        return runCatching {
            userRemoteDataSource.getMyHobby(token = token).handleApiResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun getOthersHobby(token: String, userNo: Int): Result<Hobby> {
        return runCatching {
            userRemoteDataSource.getOthersHobby(token = token, userNo = userNo).handleApiResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun updateUserInfo(
        token: String,
        password: String?,
        hobby: String?
    ): Result<Unit> {
        return runCatching {
            userRemoteDataSource.updateUserInfo(token = token, userInfoUpdateRequestDto = UserInfoUpdateRequestDto(
                password = password,
                hobby = hobby
            )).handleApiResponse().getOrThrow()
        }
    }
}