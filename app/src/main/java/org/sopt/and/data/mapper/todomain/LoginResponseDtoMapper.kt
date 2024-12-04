package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.LoginResponseDto
import org.sopt.and.domain.model.Token

fun LoginResponseDto.toDomain(): Token = Token(
    token = this.token
)