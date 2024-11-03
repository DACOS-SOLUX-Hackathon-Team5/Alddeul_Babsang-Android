package com.hackathon.alddeul_babsang.app

import com.hackathon.alddeul_babsang.data.datasource.ExampleDataSource
import com.hackathon.alddeul_babsang.data.datasourceimpl.ExampleDataSourceImpl
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
}