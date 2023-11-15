package dev.passerby.data.mappers

import dev.passerby.data.models.db.AboutTheHotelDbModel
import dev.passerby.data.models.db.HotelDbModel
import dev.passerby.data.models.dto.AboutTheHotelDto
import dev.passerby.data.models.dto.HotelDto
import dev.passerby.domain.models.AboutTheHotelModel
import dev.passerby.domain.models.HotelModel

class HotelMapper {

    fun mapHotelDtoToDbModel(dto: HotelDto) = HotelDbModel(
        aboutTheHotel = mapHotelAboutToDbModel(dto.about_the_hotel),
        address = dto.adress,
        id = dto.id,
        imageUrls = dto.image_urls,
        minimalPrice = dto.minimal_price,
        name = dto.name,
        priceForIt = dto.price_for_it,
        rating = dto.rating,
        ratingName = dto.rating_name,
    )

    fun mapHotelDbModelToEntity(dbModel: HotelDbModel) = HotelModel(
        aboutTheHotel = mapHotelAboutToEntity(dbModel.aboutTheHotel),
        address = dbModel.address,
        id = dbModel.id,
        imageUrls = dbModel.imageUrls,
        minimalPrice = dbModel.minimalPrice,
        name = dbModel.name,
        priceForIt = dbModel.priceForIt,
        rating = dbModel.rating,
        ratingName = dbModel.ratingName
    )

    private fun mapHotelAboutToDbModel(dto: AboutTheHotelDto) = AboutTheHotelDbModel(
        description = dto.description,
        peculiarities = dto.peculiarities,
    )

    private fun mapHotelAboutToEntity(dbModel: AboutTheHotelDbModel) = AboutTheHotelModel(
        description = dbModel.description,
        peculiarities = dbModel.peculiarities,
    )
}