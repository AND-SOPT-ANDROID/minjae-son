package org.sopt.and.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto (
    @SerialName("result") val result: LoginResult
)

@Serializable
data class LoginResult(
    @SerialName("token") val token: String
)