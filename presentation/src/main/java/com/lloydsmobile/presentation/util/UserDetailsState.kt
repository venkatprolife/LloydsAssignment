package com.lloydsmobile.presentation.util

import com.lloydsmobile.domain.model.UserModel

/**
 * State class to maintain the Details UI state
 */
data class UserDetailsState(
    val isLoading: Boolean = false,
    val data: UserModel? = null,
    val error: String = "",
)
