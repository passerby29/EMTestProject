package dev.passerby.data.network

import dev.passerby.data.models.dto.BookDto
import dev.passerby.data.models.dto.HotelDto
import dev.passerby.data.models.dto.RoomDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8/")
    suspend fun loadBookInfo(): Response<BookDto>

    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3/")
    suspend fun loadHotelInfo(): Response<HotelDto>

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd/")
    suspend fun loadRoomsInfo(): Response<List<RoomDto>>
}