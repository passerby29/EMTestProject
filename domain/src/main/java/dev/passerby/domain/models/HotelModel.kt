package dev.passerby.domain.models

data class HotelModel(
    val aboutTheHotel: AboutTheHotelModel,
    val address: String,
    val id: Int,
    val imageUrls: List<String>,
    val minimalPrice: Int,
    val name: String,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String
)