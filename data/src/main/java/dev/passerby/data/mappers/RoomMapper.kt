package dev.passerby.data.mappers

import dev.passerby.data.models.db.RoomDbModel
import dev.passerby.data.models.dto.RoomDto
import dev.passerby.domain.models.RoomModel

class RoomMapper {

    fun mapRoomDtoToDbModel(dto: RoomDto) = RoomDbModel(
        id = dto.id,
        imageUrls = dto.image_urls,
        name = dto.name,
        peculiarities = dto.peculiarities,
        price = dto.price,
        pricePer = dto.price_per,
    )

    fun mapRoomDbModelToEntity(dbModel: RoomDbModel) = RoomModel(
        id = dbModel.id,
        imageUrls = dbModel.imageUrls,
        name = dbModel.name,
        peculiarities = dbModel.peculiarities,
        price = dbModel.price,
        pricePer = dbModel.pricePer,
    )
}