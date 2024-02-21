package com.lloydsmobile.data.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ErrorInterceptor
    @Inject
    constructor() : Interceptor {
        private val TAG = ErrorInterceptor::class.qualifiedName

        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val response = chain.proceed(request)
            Log.d(TAG, "Response code: ${response.code()}")
            when (response.code()) {
                400 -> {
                    Log.d(TAG, "Show Bad Request Error Message")
                }
                401 -> {
                    Log.d(TAG, "Show UnauthorizedError Message")
                }
                403 -> {
                    Log.d(TAG, "Show Forbidden Message")
                }
                404 -> {
                    Log.d(TAG, "Show NotFound Message")
                }
            }
            return response
        }
    }
