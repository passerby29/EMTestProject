package dev.passerby.emtestproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.passerby.domain.usecases.GetBookInfoUseCase
import dev.passerby.domain.usecases.LoadBookInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    getBookInfoUseCase: GetBookInfoUseCase,
    private val loadBookInfoUseCase: LoadBookInfoUseCase
) : ViewModel() {

    val bookInfo = getBookInfoUseCase()

    init {
        viewModelScope.launch {
            loadBookInfoUseCase()
        }
    }

}