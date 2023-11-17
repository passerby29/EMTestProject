package dev.passerby.domain.repos

import androidx.lifecycle.LiveData
import dev.passerby.domain.models.BookModel
import dev.passerby.domain.models.HotelModel
import dev.passerby.domain.models.RoomModel

interface MainRepository {

    fun getHotelInfo(): LiveData<List<HotelModel>>
    fun getRoomsInfo(): LiveData<List<RoomModel>>
    fun getBookInfo(): LiveData<List<BookModel>>

    suspend fun loadHotelInfo()
    suspend fun loadRoomsInfo()
    suspend fun loadBookInfo()
}