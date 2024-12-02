package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.HobbyResponseDto
import org.sopt.and.domain.model.Hobby

fun HobbyResponseDto.toDomain(): Hobby =  Hobby(
    hobby = this.hobby
)