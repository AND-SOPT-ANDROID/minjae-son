package org.sopt.and.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationRequestDto (
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("hobby") val hobby: String,
)