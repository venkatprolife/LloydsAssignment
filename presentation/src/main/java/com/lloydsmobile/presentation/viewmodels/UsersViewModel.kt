package com.lloydsmobile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.usecases.GetUserUseCase
import com.lloydsmobile.domain.util.Resource
import com.lloydsmobile.presentation.util.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel
@Inject
constructor(private val userUseCase: GetUserUseCase) : ViewModel() {
    private val _userListState = MutableStateFlow(UserListState())
    var userListState: StateFlow<UserListState> = _userListState

    fun getUserList() {
        viewModelScope.launch {
            when (val resource= userUseCase.getUsers()) {
                is Resource.Success -> {
                    _userListState.value = UserListState(data = resource.data)
                }

                is Resource.Error -> {
                    _userListState.value = UserListState(error = resource.message.toString())
                }
            }

        }
    }

}
