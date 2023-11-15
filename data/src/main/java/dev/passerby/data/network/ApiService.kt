package dev.passerby.data.network

import dev.passerby.data.models.dto.BookDto
import dev.passerby.data.models.dto.HotelDto
import dev.passerby.data.models.dto.RoomDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff/")
    suspend fun loadBookInfo(): Response<BookDto>

    @GET("d144777c-a67f-4e35-867a-cacc3b827473/")
    suspend fun loadHotelInfo(): Response<HotelDto>

    @GET("8b532701-709e-4194-a41c-1a903af00195/")
    suspend fun loadRoomsInfo(): Response<List<RoomDto>>
}