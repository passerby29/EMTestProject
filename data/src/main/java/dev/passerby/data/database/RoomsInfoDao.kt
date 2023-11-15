package dev.passerby.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.passerby.data.models.db.RoomDbModel

interface RoomsInfoDao {

    @Query("select * from room")
    fun getRoomsInfo(): LiveData<List<RoomDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(roomsInfo: List<RoomDbModel>)
}