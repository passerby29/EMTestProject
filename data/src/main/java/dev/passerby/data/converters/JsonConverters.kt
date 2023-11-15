package dev.passerby.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import dev.passerby.data.models.db.AboutTheHotelDbModel

class JsonConverters {
    @TypeConverter
    fun stringListToJson(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun aboutHotelClassToJson(value: AboutTheHotelDbModel): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToAboutHotelClass(value: String): AboutTheHotelDbModel =
        Gson().fromJson(value, AboutTheHotelDbModel::class.java)
}