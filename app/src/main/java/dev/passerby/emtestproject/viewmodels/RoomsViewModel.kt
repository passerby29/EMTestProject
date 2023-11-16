package dev.passerby.emtestproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.passerby.data.repos.MainRepositoryImpl
import dev.passerby.domain.usecases.GetRoomsInfoUseCase
import dev.passerby.domain.usecases.LoadRoomsInfoUseCase
import kotlinx.coroutines.launch

class RoomsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val getRoomsInfoUseCase = GetRoomsInfoUseCase(repository)
    private val loadRoomsInfoUseCase = LoadRoomsInfoUseCase(repository)

    val roomsInfo = getRoomsInfoUseCase()

    init {
        viewModelScope.launch {
            loadRoomsInfoUseCase()
        }
    }
}