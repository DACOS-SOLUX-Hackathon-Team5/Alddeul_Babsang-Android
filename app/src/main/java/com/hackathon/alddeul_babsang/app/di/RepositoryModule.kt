package com.hackathon.alddeul_babsang.app.di

import com.hackathon.alddeul_babsang.data.repositoryimpl.ExampleRepositoryImpl
import com.hackathon.alddeul_babsang.data.repositoryimpl.UserPreferencesRepositoryImpl
import com.hackathon.alddeul_babsang.domain.repository.ExampleRepository
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
}