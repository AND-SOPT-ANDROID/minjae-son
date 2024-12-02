package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.UserRegistrationResponseDto
import org.sopt.and.domain.model.UserNo

fun UserRegistrationResponseDto.toDomain(): UserNo = UserNo(
    no = this.no
)