package org.sopt.and.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserRegistrationDto(
    @SerialName("result") val result: UserRegistrationResult
)

@Serializable
data class UserRegistrationResult(
    @SerialName("no") val no: Int
)