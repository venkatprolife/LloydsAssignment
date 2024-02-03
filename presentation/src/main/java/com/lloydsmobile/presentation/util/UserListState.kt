package com.lloydsmobile.presentation.util

import com.lloydsmobile.domain.model.UserListModel

data class UserListState(
    val isLoading: Boolean = false,
    val data: UserListModel? = null,
    val error: String = "",
)
