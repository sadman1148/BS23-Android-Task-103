package com.bs23.androidtest103.di

import com.bs23.androidtest103.data.Repository
import com.bs23.androidtest103.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }
}