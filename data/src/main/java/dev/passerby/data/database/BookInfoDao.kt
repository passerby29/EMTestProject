package dev.passerby.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.passerby.data.models.db.BookDbModel

@Dao
interface BookInfoDao {

    @Query("select * from book")
    fun getBookInfo(): LiveData<List<BookDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookInfo: BookDbModel)
}