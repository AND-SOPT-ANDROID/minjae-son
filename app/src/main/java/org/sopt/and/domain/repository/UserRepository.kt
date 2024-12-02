package org.sopt.and.domain.repository

import org.sopt.and.domain.model.Hobby

interface UserRepository {
    suspend fun getMyHobby(token: String): Result<Hobby>
    suspend fun getOthersHobby(token: String, userNo: Int): Result<Hobby>
    suspend fun updateUserInfo(token: String, password: String?, hobby: String?): Result<Unit>
}