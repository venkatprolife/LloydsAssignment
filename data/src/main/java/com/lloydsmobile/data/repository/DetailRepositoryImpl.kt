package com.lloydsmobile.data.repository

import android.util.Log
import com.lloydsmobile.data.services.DetailApiService
import com.lloydsmobile.domain.mapper.toUserModel
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
            return try {
                val response =
                    detailApiService.getUserById(id)
                if (response.isSuccessful && response.body()?.data != null) {
                    Resource.Success(response.body()!!.data.toUserModel())
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
