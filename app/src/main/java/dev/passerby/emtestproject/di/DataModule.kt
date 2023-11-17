package dev.passerby.emtestproject.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.passerby.data.network.ApiService
import dev.passerby.data.repos.MainRepositoryImpl
import dev.passerby.domain.repos.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService, application: Application): MainRepository {
        return MainRepositoryImpl(apiService, application)
    }
}