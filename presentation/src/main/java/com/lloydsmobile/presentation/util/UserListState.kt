package com.lloydsmobile.presentation.util

import com.lloydsmobile.domain.model.UserModel

/**
 * State class to maintain the Details UI state
 */
data class UserListState(
    val isLoading: Boolean = false,
    val data: List<UserModel> = emptyList(),
    val error: String = "",
)
