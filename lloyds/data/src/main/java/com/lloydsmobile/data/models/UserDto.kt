package com.lloydsmobile.data.models

data class UserDto(
    val avatar: String = "",
    val email: String = "",
    val first_name: String = "",
    val id: Int = 0,
    val last_name: String = "defaultLastName"
)