package org.sopt.and.data.mapper.todata

import org.sopt.and.data.remote.model.request.LoginRequestDto
import org.sopt.and.data.remote.model.request.UserInfoUpdateRequestDto
import org.sopt.and.data.remote.model.request.UserRegistrationRequestDto
import org.sopt.and.domain.model.User

fun User.toUserRegistrationRequestDto(): UserRegistrationRequestDto = UserRegistrationRequestDto(
    username = this.username,
    password = this.password,
    hobby = this.hobby
)

fun User.toLoginRequestDto(): LoginRequestDto = LoginRequestDto(
    username = this.username,
    password = this.password
)


fun User.toUserInfoUpdateRequestDto(): UserInfoUpdateRequestDto = UserInfoUpdateRequestDto(
    hobby = this.hobby,
    password = this.password
)