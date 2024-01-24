package com.lloydsmobile.domain.di

import com.lloydsmobile.domain.repository.DetailRepositoryDomainImpl
import com.lloydsmobile.domain.repository.UserRepositoryDomainImpl
import com.lloydsmobile.data.repository.DetailRepository
import com.lloydsmobile.domain.repository.DetailRepositoryDomain
import com.lloydsmobile.data.repository.UserRepository
import com.lloydsmobile.domain.repository.UserRepositoryDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideUserRepoDomain(userRepository: UserRepository) : UserRepositoryDomain {
        return UserRepositoryDomainImpl(userRepository)
    }

    @Provides
    fun provideDetailRepoDomain(detailRepository: DetailRepository) : DetailRepositoryDomain {
        return DetailRepositoryDomainImpl(detailRepository)
    }
}