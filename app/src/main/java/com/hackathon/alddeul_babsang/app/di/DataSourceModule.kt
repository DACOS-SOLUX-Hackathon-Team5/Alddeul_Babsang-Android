package com.hackathon.alddeul_babsang.app.di

import com.hackathon.alddeul_babsang.data.datasource.BabsangDataSource
import com.hackathon.alddeul_babsang.data.datasource.DetailDataSource
import com.hackathon.alddeul_babsang.data.datasource.ExampleDataSource
import com.hackathon.alddeul_babsang.data.datasource.MapDataSource
import com.hackathon.alddeul_babsang.data.datasource.ProfileDataSource
import com.hackathon.alddeul_babsang.data.datasource.ReportDataSource
import com.hackathon.alddeul_babsang.data.datasource.UserPreferencesDataSource
import com.hackathon.alddeul_babsang.data.datasourceimpl.BabsangDataSourceImpl
import com.hackathon.alddeul_babsang.data.datasourceimpl.DetailDataSourceImpl
import com.hackathon.alddeul_babsang.data.datasourceimpl.ExampleDataSourceImpl
import com.hackathon.alddeul_babsang.data.datasourceimpl.MapDataSourceImpl
import com.hackathon.alddeul_babsang.data.datasourceimpl.ProfileDataSourceImpl
import com.hackathon.alddeul_babsang.data.datasourceimpl.ReportDataSourceImpl
import com.hackathon.alddeul_babsang.data.datasourceimpl.UserPreferencesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindExampleDataSource(exampleDataSourceImpl: ExampleDataSourceImpl): ExampleDataSource

    @Binds
    @Singleton
    abstract fun bindUserPreferencesDataSource(userPreferencesDataSourceImpl: UserPreferencesDataSourceImpl): UserPreferencesDataSource

    @Binds
    @Singleton
    abstract fun bindBabsangDataSource(babsangDataSourceImpl: BabsangDataSourceImpl): BabsangDataSource

    @Binds
    @Singleton
    abstract fun bindProfileDataSource(profileDataSourceImpl: ProfileDataSourceImpl): ProfileDataSource

    @Binds
    @Singleton
    abstract fun bindDetailDataSource(detailDataSourceImpl: DetailDataSourceImpl): DetailDataSource

    @Binds
    @Singleton
    abstract fun bindMapDataSource(mapDataSourceImpl: MapDataSourceImpl): MapDataSource

    @Binds
    @Singleton
    abstract fun bindReportDataSource(reportDataSourceImpl: ReportDataSourceImpl): ReportDataSource

}