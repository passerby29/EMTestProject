package dev.passerby.data.models.dto


import com.google.gson.annotations.SerializedName

data class RoomListDto(
    @SerializedName("rooms")
    val rooms: List<RoomDto>
)