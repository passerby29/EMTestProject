package dev.passerby.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room")
data class RoomDbModel(
    @PrimaryKey
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
)