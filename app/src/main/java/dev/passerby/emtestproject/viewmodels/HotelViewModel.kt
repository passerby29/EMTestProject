package dev.passerby.emtestproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.passerby.domain.usecases.GetHotelInfoUseCase
import dev.passerby.domain.usecases.LoadHotelInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    getHotelInfoUseCase: GetHotelInfoUseCase,
    private val loadHotelInfoUseCase: LoadHotelInfoUseCase
) : ViewModel() {

    val hotelInfo = getHotelInfoUseCase()

    init {
        viewModelScope.launch {
            loadHotelInfoUseCase()
        }
    }
}