package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.local.datasource.TokenLocalDataSource
import org.sopt.and.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource
) : TokenRepository {
    override fun getToken(): String = tokenLocalDataSource.token

    override fun setToken(token: String) {
        tokenLocalDataSource.token = token
    }

    override fun removeToken() {
        tokenLocalDataSource.removeToken()
    }
}