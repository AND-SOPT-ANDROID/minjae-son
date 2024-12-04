package org.sopt.and.data.local.datasource

interface TokenLocalDataSource {
    var token: String
    fun removeToken()
}