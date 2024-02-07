package com.lloydsmobile.data.repository

import android.util.Log
import com.lloydsmobile.data.mapper.toUserListModel
import com.lloydsmobile.data.services.UsersApiService
import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.repository.UserRepository
import com.lloydsmobile.domain.util.Resource
import org.json.JSONObject
import javax.inject.Inject

class UserRepositoryImpl
    @Inject
    constructor(private val usersApiService: UsersApiService) :
    UserRepository {
        override suspend fun getUsers(): Resource<UserListModel> {
            try {
                val response = usersApiService.getUsers()
                if (response.isSuccessful && response.body() != null) {
                    return Resource.Success(response.body()?.toUserListModel())
                } else if (response.errorBody() != null) {
                    val errorBody = response.errorBody()?.charStream()
                    if (errorBody != null) {
                        val errorObj = JSONObject(errorBody.readText())
                        return Resource.Error(errorObj.getString("message"))
                    }
                }
            } catch (e: Exception) {
                Log.d("Venkat", e.toString())
                return Resource.Error("Something Went Wrong")
            }
            return Resource.Error("Something Went Wrong")
        }
    }
