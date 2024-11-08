package com.hackathon.alddeul_babsang.app.di

import com.hackathon.alddeul_babsang.data.repositoryimpl.BabsangRepositoryImpl
import com.hackathon.alddeul_babsang.data.repositoryimpl.DetailRepositoryImpl
import com.hackathon.alddeul_babsang.data.repositoryimpl.ExampleRepositoryImpl
import com.hackathon.alddeul_babsang.data.repositoryimpl.MapRepositoryImpl
import com.hackathon.alddeul_babsang.data.repositoryimpl.ProfileRepositoryImpl
import com.hackathon.alddeul_babsang.data.repositoryimpl.UserPreferencesRepositoryImpl
import com.hackathon.alddeul_babsang.domain.repository.BabsangRepository
import com.hackathon.alddeul_babsang.domain.repository.DetailRepository
import com.hackathon.alddeul_babsang.domain.repository.ExampleRepository
import com.hackathon.alddeul_babsang.domain.repository.MapRepository
import com.hackathon.alddeul_babsang.domain.repository.ProfileRepository
import com.hackathon.alddeul_babsang.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExampleRepository(exampleRepositoryImpl: ExampleRepositoryImpl): ExampleRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl): UserPreferencesRepository

    @Binds
    @Singleton
    abstract fun bindBabsangRepository(babsangRepositoryImpl: BabsangRepositoryImpl): BabsangRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository

    @Binds
    @Singleton
    abstract fun bindMapRepository(mapRepositoryImpl: MapRepositoryImpl): MapRepository
}