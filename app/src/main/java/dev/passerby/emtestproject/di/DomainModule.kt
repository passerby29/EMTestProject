package dev.passerby.emtestproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.passerby.domain.repos.MainRepository
import dev.passerby.domain.usecases.GetBookInfoUseCase
import dev.passerby.domain.usecases.GetHotelInfoUseCase
import dev.passerby.domain.usecases.GetRoomsInfoUseCase
import dev.passerby.domain.usecases.LoadBookInfoUseCase
import dev.passerby.domain.usecases.LoadHotelInfoUseCase
import dev.passerby.domain.usecases.LoadRoomsInfoUseCase

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

    @Provides
    fun provideGetRoomsInfoUseCase(mainRepository: MainRepository): GetRoomsInfoUseCase {
        return GetRoomsInfoUseCase(mainRepository)
    }

    @Provides
    fun provideLoadRoomsInfoUseCase(mainRepository: MainRepository): LoadRoomsInfoUseCase{
        return LoadRoomsInfoUseCase(mainRepository)
    }

    @Provides
    fun provideGetBookInfoUseCase(mainRepository: MainRepository): GetBookInfoUseCase {
        return GetBookInfoUseCase(mainRepository)
    }

    @Provides
    fun provideLoadBookInfoUseCase(mainRepository: MainRepository): LoadBookInfoUseCase {
        return LoadBookInfoUseCase(mainRepository)
    }
}