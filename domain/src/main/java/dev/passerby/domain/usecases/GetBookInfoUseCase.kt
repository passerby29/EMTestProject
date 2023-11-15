package dev.passerby.domain.usecases

import dev.passerby.domain.repos.MainRepository

class GetBookInfoUseCase(private val repository: MainRepository) {
    operator fun invoke() = repository.getBookInfo()
}