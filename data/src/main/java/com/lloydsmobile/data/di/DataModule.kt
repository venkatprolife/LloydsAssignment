package com.lloydsmobile.data.di

import com.lloydsmobile.data.repository.DetailRepositoryImpl
import com.lloydsmobile.data.repository.UserRepositoryImpl
import com.lloydsmobile.data.services.DetailApiService
import com.lloydsmobile.data.services.UsersApiService
import com.lloydsmobile.domain.repository.DetailRepository
import com.lloydsmobile.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideUserRepo(usersApiService: UsersApiService) : UserRepository {
        return UserRepositoryImpl(usersApiService)
    }

    @Singleton
    @Provides
    fun provideDetailRepo(detailApiService: DetailApiService) : DetailRepository {
        return DetailRepositoryImpl(detailApiService)
    }
}
