package com.lloydsmobile.data.models

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("avatar")
    val url: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
)
