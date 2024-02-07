package com.lloydsmobile.data.di

import com.lloydsmobile.data.services.DetailApiService
import com.lloydsmobile.data.services.UsersApiService
import com.lloydsmobile.data.utils.ErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://reqres.in/"

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create(),
        ).build()
    }

    @Provides
    fun provideOkHttpClient(errorInterceptor: ErrorInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(errorInterceptor).build()
    }

    @Provides
    fun provideUsersAPI(retrofit: Retrofit): UsersApiService {
        return retrofit.create(UsersApiService::class.java)
    }

    @Provides
    fun provideDetailAPI(retrofit: Retrofit): DetailApiService {
        return retrofit.create(DetailApiService::class.java)
    }
}
