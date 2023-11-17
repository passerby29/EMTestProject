package dev.passerby.emtestproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.passerby.domain.repos.MainRepository
import dev.passerby.domain.usecases.GetHotelInfoUseCase
import dev.passerby.domain.usecases.LoadHotelInfoUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetHotelInfoUseCase(mainRepository: MainRepository): GetHotelInfoUseCase{
        return GetHotelInfoUseCase(mainRepository)
    }

    @Provides
    fun provideLoadHotelInfoUseCase(mainRepository: MainRepository): LoadHotelInfoUseCase{
        return LoadHotelInfoUseCase(mainRepository)
    }
}