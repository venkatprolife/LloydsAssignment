package com.lloydsmobile.data.di

import com.lloydsmobile.data.repository.DetailRepository
import com.lloydsmobile.data.repository.DetailRepositoryImpl
import com.lloydsmobile.data.repository.UserRepository
import com.lloydsmobile.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun provideUserRepo(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun provideDetailRepo(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}
