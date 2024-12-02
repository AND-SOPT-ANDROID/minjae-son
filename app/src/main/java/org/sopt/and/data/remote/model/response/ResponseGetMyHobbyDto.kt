package org.sopt.and.data.remote.model.response

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