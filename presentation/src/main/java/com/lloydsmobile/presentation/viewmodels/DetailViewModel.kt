package com.lloydsmobile.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.usecases.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        private val getUserDetailUseCase: GetUserDetailUseCase,
        private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _userModel = MutableStateFlow(UserModel())
        val userModel: StateFlow<UserModel?> get() = _userModel
        private val userId = "userid"

        fun getUser() {
            viewModelScope.launch {
                val userid = savedStateHandle.get<String>(userId) ?: "2"
                val result = getUserDetailUseCase.getUserById(userid)
                _userModel.emit(result.value)
            }
        }
    }
