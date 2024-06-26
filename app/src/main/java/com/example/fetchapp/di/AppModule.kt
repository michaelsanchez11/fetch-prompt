package com.example.fetchapp.di

import com.example.fetchapp.repository.DataRepository
import com.example.fetchapp.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesRepository(dataRepository: DataRepositoryImpl): DataRepository {
        return dataRepository
    }
}