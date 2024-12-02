package org.sopt.and.domain.repository

import org.sopt.and.domain.model.Token
import org.sopt.and.domain.model.User
import org.sopt.and.domain.model.UserNo

interface AuthRepository {
    suspend fun registerUser(user: User): Result<UserNo>
    suspend fun login(username: String, password: String): Result<Token>
}