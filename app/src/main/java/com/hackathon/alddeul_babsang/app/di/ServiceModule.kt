package com.hackathon.alddeul_babsang.app.di

import com.hackathon.alddeul_babsang.data.service.BabsangApiService
import com.hackathon.alddeul_babsang.data.service.ReportApiService
import com.hackathon.alddeul_babsang.data.service.DetailApiService
import com.hackathon.alddeul_babsang.data.service.ProfileApiService
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

    @Provides
    @Singleton
    fun provideBabsangService(
        @AlddeulRetrofit retrofit: Retrofit
    ): BabsangApiService = retrofit.create(BabsangApiService::class.java)

    @Provides
    @Singleton
    fun provideProfileService(
        @AlddeulRetrofit retrofit: Retrofit
    ): ProfileApiService = retrofit.create(ProfileApiService::class.java)

    @Provides
    @Singleton
    fun provideDetailService(
        @AlddeulRetrofit retrofit: Retrofit
    ): DetailApiService = retrofit.create(DetailApiService::class.java)

    @Provides
    @Singleton
    fun provideReportService(
        @AlddeulRetrofit retrofit: Retrofit
    ): ReportApiService = retrofit.create(ReportApiService::class.java)

    @Provides
    @Singleton
    fun provideReportWriteService(
        @AlddeulRetrofit retrofit: Retrofit
    ): ReportApiService = retrofit.create(ReportApiService::class.java)
}
