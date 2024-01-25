package com.lloydsmobile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val userUseCase: GetUserUseCase): ViewModel() {
    private val _userListModel = MutableStateFlow(UserListModel())
    var userList : StateFlow<UserListModel> = _userListModel

    fun getUserList() {
        viewModelScope.launch {
            val result = userUseCase.getUsers()
            _userListModel.emit(result)
        }
    }
}