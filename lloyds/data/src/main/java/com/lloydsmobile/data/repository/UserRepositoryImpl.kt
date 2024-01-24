package com.lloydsmobile.data.repository

import android.util.Log
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.services.UsersApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val usersApiService: UsersApiService) :
    UserRepository {

    private val _userListDto = MutableStateFlow(UsersListDto())
    override val userListDto:StateFlow<UsersListDto> get() = _userListDto

    override suspend fun getUsers() {
        try {
            val response = usersApiService.getUsers()
            if(response.isSuccessful && response.body()?.data != null) {
                _userListDto.emit(response.body()!!)
            }
        } catch (e: Exception) {
            Log.d("Venkat", e.toString())
        }
    }
}