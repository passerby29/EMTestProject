package dev.passerby.data.mappers

import dev.passerby.data.models.db.BookDbModel
import dev.passerby.data.models.dto.BookDto
import dev.passerby.domain.models.BookModel

class BookMapper {

    fun mapBookDtoToDbModel(dto: BookDto) = BookDbModel(
        arrivalCountry = dto.arrival_country,
        departure = dto.departure,
        fuelCharge = dto.fuel_charge,
        hoRating = dto.horating,
        hotelAddress = dto.hotel_adress,
        hotelMame = dto.hotel_name,
        id = dto.id,
        numberOfNights = dto.number_of_nights,
        nutrition = dto.nutrition,
        ratingName = dto.rating_name,
        room = dto.room,
        serviceCharge = dto.service_charge,
        tourDateStart = dto.tour_date_start,
        tourDateStop = dto.tour_date_stop,
        tourPrice = dto.tour_price,
    )

    fun mapBookDbModelToEntity(dbModel: BookDbModel) = BookModel(
        arrivalCountry = dbModel.arrivalCountry,
        departure = dbModel.departure,
        fuelCharge = dbModel.fuelCharge,
        hoRating = dbModel.hoRating,
        hotelAddress = dbModel.hotelAddress,
        hotelMame = dbModel.hotelMame,
        id = dbModel.id,
        numberOfNights = dbModel.numberOfNights,
        nutrition = dbModel.nutrition,
        ratingName = dbModel.ratingName,
        room = dbModel.room,
        serviceCharge = dbModel.serviceCharge,
        tourDateStart = dbModel.tourDateStart,
        tourDateStop = dbModel.tourDateStop,
        tourPrice = dbModel.tourPrice
    )
}