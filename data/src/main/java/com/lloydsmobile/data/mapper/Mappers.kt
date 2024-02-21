package com.lloydsmobile.data.mapper

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.domain.model.UserModel

/**
 * Mapper class to convert dto to model objects
 */
fun UserDto.toUserModel(): UserModel {
    return UserModel(
        firstName = this.firstName,
        lastName = this.lastName,
        url = this.url,
        email = this.email,
        id = this.id,
    )
}

fun UsersListDto.toUserListModel(): List<UserModel> {
    return this.data.map {
        it
    }.map { userDto ->
        with(userDto) {
            UserModel(
                firstName = firstName,
                lastName = lastName,
                email = email,
                url = url,
                id = id,
            )
        }
    }
}
