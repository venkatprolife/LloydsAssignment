package com.lloydsmobile.presentation.util

import com.lloydsmobile.domain.model.UserListModel

data class UserListState(
    val isLoading: Boolean = false,
    val data: UserListModel = UserListModel(emptyList()),
    val error: String = "",
)
