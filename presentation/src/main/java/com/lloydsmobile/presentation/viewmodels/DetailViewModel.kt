package com.lloydsmobile.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydsmobile.domain.usecases.GetDetailsUseCase
import com.lloydsmobile.domain.util.Resource
import com.lloydsmobile.presentation.USER_ID
import com.lloydsmobile.presentation.util.UserDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        private val getDetailsUseCase: GetDetailsUseCase,
        private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _userDetailsState = MutableStateFlow(UserDetailsState())
        var userDetailsState: StateFlow<UserDetailsState> = _userDetailsState
        private val userId = USER_ID

        fun getUser() {
            viewModelScope.launch {
                _userDetailsState.update { it.copy(isLoading = true) }
                val id = savedStateHandle.get<String>(userId)
                if (id != null) {
                    when (val resource = getDetailsUseCase(id)) {
                        is Resource.Success -> {
                            _userDetailsState.value = UserDetailsState(data = resource.data)
                        }

                        is Resource.Error -> {
                            _userDetailsState.value =
                                UserDetailsState(error = resource.message.toString())
                        }
                    }
                } else {
                    _userDetailsState.value =
                        UserDetailsState(error = "Something went wrong")
                }
            }
        }
    }
