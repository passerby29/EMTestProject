package dev.passerby.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.passerby.data.converters.JsonConverters
import dev.passerby.data.models.db.BookDbModel
import dev.passerby.data.models.db.HotelDbModel
import dev.passerby.data.models.db.RoomDbModel

@Database(
    entities = [BookDbModel::class, HotelDbModel::class, RoomDbModel::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(JsonConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookInfoDao(): BookInfoDao
    abstract fun hotelInfoDao(): HotelInfoDao
    abstract fun roomsInfoDao(): RoomsInfoDao

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }
}