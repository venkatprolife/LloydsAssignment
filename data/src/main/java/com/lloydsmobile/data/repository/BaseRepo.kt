package com.lloydsmobile.data.repository

import com.lloydsmobile.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

/**
 * Common abstract class to handle API response and errors
 */
abstract class BaseRepo {
    private val errorMessage = "Something Went Wrong"
    private val message = "message"

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    Resource.Success(data = response.body()!!)
                } else if (response.errorBody() != null) {
                    val errorBody = response.errorBody()?.charStream()
                    if (errorBody != null) {
                        val errorObj = JSONObject(errorBody.readText())
                        Resource.Error(errorObj.getString(message))
                    } else {
                        Resource.Error(errorMessage)
                    }
                } else {
                    Resource.Error(errorMessage)
                }
            } catch (e: Exception) {
                Resource.Error(errorMessage)
            }
        }
    }
}
