package dev.passerby.emtestproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.passerby.data.repos.MainRepositoryImpl
import dev.passerby.domain.usecases.GetBookInfoUseCase
import dev.passerby.domain.usecases.LoadBookInfoUseCase
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val getBookInfoUseCase = GetBookInfoUseCase(repository)
    private val loadBookInfoUseCase = LoadBookInfoUseCase(repository)

    val bookInfo = getBookInfoUseCase()

    init {
        viewModelScope.launch {
            loadBookInfoUseCase()
        }
    }

}