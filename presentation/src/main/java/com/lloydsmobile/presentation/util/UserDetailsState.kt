package com.lloydsmobile.presentation.util

import com.lloydsmobile.domain.model.UserModel

data class UserDetailsState(
    val isLoading: Boolean = true,
    val data: UserModel? = null,
    val error: String = ""
)