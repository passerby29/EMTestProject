package dev.passerby.domain.usecases

import dev.passerby.domain.repos.MainRepository

class GetHotelInfoUseCase(private val repository: MainRepository) {
    operator fun invoke() = repository.getHotelInfo()
}