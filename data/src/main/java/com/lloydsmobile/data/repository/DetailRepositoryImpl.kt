package com.lloydsmobile.data.repository

import android.util.Log
import com.lloydsmobile.data.mapper.toUserModel
import com.lloydsmobile.data.services.DetailApiService
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.DetailRepository
import com.lloydsmobile.domain.util.Resource
import org.json.JSONObject
import javax.inject.Inject

class DetailRepositoryImpl
    @Inject
    constructor(private val detailApiService: DetailApiService) :
    DetailRepository {
        override suspend fun getUserById(id: String): Resource<UserModel> {
            try {
                val response =
                    detailApiService.getUserById(id)
                if (response.isSuccessful && response.body() != null) {
                    val userDto = response.body()?.data
                    if (userDto != null) {
                        return Resource.Success(userDto.toUserModel())
                    }
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
