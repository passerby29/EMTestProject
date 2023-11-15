package dev.passerby.domain.usecases

import dev.passerby.domain.repos.MainRepository

class GetRoomsInfoUseCase(private val repository: MainRepository) {
    operator fun invoke() = repository.getRoomsInfo()
}