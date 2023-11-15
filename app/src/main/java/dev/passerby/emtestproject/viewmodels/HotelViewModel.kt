package dev.passerby.emtestproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.passerby.data.repos.MainRepositoryImpl
import dev.passerby.domain.usecases.GetHotelInfoUseCase
import dev.passerby.domain.usecases.LoadHotelInfoUseCase
import kotlinx.coroutines.launch

class HotelViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val getHotelInfoUseCase = GetHotelInfoUseCase(repository)
    private val loadHotelInfoUseCase = LoadHotelInfoUseCase(repository)

    val hotelInfo = getHotelInfoUseCase()

    init {
        viewModelScope.launch {
            loadHotelInfoUseCase()
        }
    }
}