package org.sopt.and.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMyHobbyDto(
    @SerialName("result") val result: GetMyHobbyResult
)

@Serializable
data class GetMyHobbyResult(
    @SerialName("hobby") val hobby: String
)