package com.lloydsmobile.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydsmobile.domain.usecases.GetUserDetailUseCase
import com.lloydsmobile.domain.util.Resource
import com.lloydsmobile.presentation.util.UserDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val userDetailUseCase: GetUserDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _userDetailsState = MutableStateFlow(UserDetailsState())
    var userDetailsState: StateFlow<UserDetailsState> = _userDetailsState
    private val userId = "userid"

    fun getUser() {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>(userId) ?: "2"
            when (val resource = userDetailUseCase.getUserById(id)) {
                is Resource.Success -> {
                    _userDetailsState.value = UserDetailsState(data = resource.data)
                }

                is Resource.Error -> {
                    _userDetailsState.value = UserDetailsState(error = resource.message.toString())
                }
            }
        }
    }
}
