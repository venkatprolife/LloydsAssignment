package com.lloydsmobile.data.di

import com.lloydsmobile.data.repository.DetailRepositoryImpl
import com.lloydsmobile.data.repository.UserRepositoryImpl
import com.lloydsmobile.data.services.DetailApiService
import com.lloydsmobile.data.services.UsersApiService
import com.lloydsmobile.domain.repository.DetailRepository
import com.lloydsmobile.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
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
