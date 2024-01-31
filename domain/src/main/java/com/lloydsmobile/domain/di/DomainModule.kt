package com.lloydsmobile.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
/*    @Provides
    fun provideUserRepoDomain(userRepository: UserRepository): UserRepositoryDomain {
        return UserRepositoryDomainImpl(userRepository)
    }

    @Provides
    fun provideDetailRepoDomain(detailRepository: DetailRepository): DetailRepositoryDomain {
        return DetailRepositoryDomainImpl(detailRepository)
    }*/
}
