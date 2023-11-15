package dev.passerby.domain.models

data class BookModel(
    val arrivalCountry: String,
    val departure: String,
    val fuelCharge: Int,
    val hoRating: Int,
    val hotelAddress: String,
    val hotelMame: String,
    val id: Int,
    val numberOfNights: Int,
    val nutrition: String,
    val ratingName: String,
    val room: String,
    val serviceCharge: Int,
    val tourDateStart: String,
    val tourDateStop: String,
    val tourPrice: Int
)