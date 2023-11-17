package dev.passerby.emtestproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.passerby.domain.usecases.GetRoomsInfoUseCase
import dev.passerby.domain.usecases.LoadRoomsInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    getRoomsInfoUseCase: GetRoomsInfoUseCase,
    private val loadRoomsInfoUseCase: LoadRoomsInfoUseCase
) : ViewModel() {

    val roomsInfo = getRoomsInfoUseCase()

    init {
        viewModelScope.launch {
            loadRoomsInfoUseCase()
        }
    }
}