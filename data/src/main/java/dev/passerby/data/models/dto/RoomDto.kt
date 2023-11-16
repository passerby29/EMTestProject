package dev.passerby.data.models.dto


import com.google.gson.annotations.SerializedName

data class RoomDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("price_per")
    val pricePer: String
)