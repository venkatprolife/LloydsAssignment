package com.lloydsmobile.data.di

import com.lloydsmobile.data.repository.DetailRepository
import com.lloydsmobile.data.repository.DetailRepositoryImpl
import com.lloydsmobile.data.repository.UserRepository
import com.lloydsmobile.data.repository.UserRepositoryImpl
import com.lloydsmobile.data.services.DetailApiService
import com.lloydsmobile.data.services.UsersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideUserRepo(usersApiService: UsersApiService): UserRepository {
        return UserRepositoryImpl(usersApiService)
    }

    @Provides
    fun provideDetailRepo(detailApiService: DetailApiService): DetailRepository {
        return DetailRepositoryImpl(detailApiService)
    }
}
