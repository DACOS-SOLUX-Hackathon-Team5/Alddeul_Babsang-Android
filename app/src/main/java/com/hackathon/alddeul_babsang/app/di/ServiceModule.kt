package com.hackathon.alddeul_babsang.app.di

import com.sopt.data.service.ExampleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideExampleService(
        @AlddeulRetrofit retrofit: Retrofit
    ): ExampleApiService = retrofit.create(ExampleApiService::class.java)
}
