package org.sopt.and.data.repository

interface TokenRepository {
    fun getToken(): String
    fun setToken(token: String)
    fun clearInfo()
}