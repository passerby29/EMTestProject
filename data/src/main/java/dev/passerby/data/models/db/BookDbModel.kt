package dev.passerby.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookDbModel(
    val arrivalCountry: String,
    val departure: String,
    val fuelCharge: Int,
    val hoRating: Int,
    val hotelAddress: String,
    val hotelMame: String,
    @PrimaryKey
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