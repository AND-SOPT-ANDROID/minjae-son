package org.sopt.and.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoUpdateRequestDto (
    @SerialName("hobby") val hobby: String?,
    @SerialName("password") val password: String?
)