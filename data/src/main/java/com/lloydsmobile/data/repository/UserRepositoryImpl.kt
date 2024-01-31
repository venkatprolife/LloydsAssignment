package com.lloydsmobile.data.repository

import android.util.Log
import com.lloydsmobile.data.services.UsersApiService
import com.lloydsmobile.domain.mapper.toUserListModel
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
            return try {
                val response = usersApiService.getUsers()
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!.toUserListModel())
                } else if (response.errorBody() != null) {
                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    Resource.Error(errorObj.getString("message"))
                } else {
                    Resource.Error("Something Went Wrong")
                }
            } catch (e: Exception) {
                Log.d("Venkat", e.toString())
                Resource.Error("Something Went Wrong")
            }
        }
    }
