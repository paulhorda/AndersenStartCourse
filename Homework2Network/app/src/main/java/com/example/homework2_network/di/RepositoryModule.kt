package com.example.homework2_network.di

import com.example.homework2_network.data.model.ListResponse
import com.example.homework2_network.data.repository.ListRepository
import com.example.homework2_network.data.sevice.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideListRepository(apiService: ApiService): ListRepository {
        return ListRepository(apiService)
    }

}