package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.mapper.todata.toUserRegistrationRequestDto
import org.sopt.and.data.mapper.todomain.toDomain
import org.sopt.and.data.remote.datasource.AuthRemoteDataSource
import org.sopt.and.data.remote.model.request.LoginRequestDto
import org.sopt.and.data.remote.util.handleApiResponse
import org.sopt.and.domain.model.Token
import org.sopt.and.domain.model.User
import org.sopt.and.domain.model.UserNo
import org.sopt.and.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
): AuthRepository {
    override suspend fun registerUser(user: User): Result<UserNo> {
        return runCatching {
            authRemoteDataSource.registerUser(userRegistrationRequestDto = user.toUserRegistrationRequestDto()).handleApiResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun login(username: String, password: String): Result<Token> {
        return runCatching {
            authRemoteDataSource.login(loginRequestDto = LoginRequestDto(
                username = username,
                password = password
            )).handleApiResponse().getOrThrow().toDomain()
        }
    }

}