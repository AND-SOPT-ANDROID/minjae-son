package org.sopt.and.domain.repository

interface TokenRepository {
    fun getToken(): String
    fun setToken(token: String)
    fun clearInfo()
}