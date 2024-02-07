package com.lloydsmobile.data.mapper

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.model.UserModel

fun UserDto.toUserModel(): UserModel {
    return UserModel(
        firstName = this.firstName,
        lastName = this.lastName,
        url = this.url,
        email = this.email,
        id = this.id,
    )
}

fun UsersListDto.toUserListModel(): UserListModel {
    val userListModel = UserListModel(emptyList())
    val userList: MutableList<UserModel> = mutableListOf()
    this.data.forEach {
        val userModel =
            UserModel(
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email,
                url = it.url,
                id = it.id,
            )
        userList.add(userModel)
    }
    userListModel.userList = userList
    return userListModel
}
