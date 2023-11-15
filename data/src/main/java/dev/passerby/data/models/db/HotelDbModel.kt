package dev.passerby.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotel")
data class HotelDbModel(
    val aboutTheHotel: AboutTheHotelDbModel,
    val address: String,
    @PrimaryKey
    val id: Int,
    val imageUrls: List<String>,
    val minimalPrice: Int,
    val name: String,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String
)