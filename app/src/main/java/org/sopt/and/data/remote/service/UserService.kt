package org.sopt.and.data.remote.service

import org.sopt.and.data.remote.model.response.ResponseGetMyHobbyDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("user/my-hobby")
    fun getMyHobby(
        @Header("token") token: String
    ): Call<ResponseGetMyHobbyDto>
}