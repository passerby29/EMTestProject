package dev.passerby.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.passerby.data.models.db.HotelDbModel

@Dao
interface HotelInfoDao {

    @Query("select * from hotel")
    fun getHotelInfo(): LiveData<HotelDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotelInfo: HotelDbModel)
}