package com.lloydsmobile.domain.mapper

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.model.UserModel

fun UserDto.toUserModel(): UserModel {
    return UserModel(
        firstName = this.first_name,
        lastName = this.last_name,
        avatar = this.avatar,
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
                firstName = it.first_name,
                lastName = it.last_name,
                email = it.email,
                avatar = it.avatar,
                id = it.id,
            )
        userList.add(userModel)
    }
    userListModel.userList = userList
    return userListModel
}