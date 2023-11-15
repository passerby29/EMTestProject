package dev.passerby.data.repos

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import dev.passerby.data.database.AppDatabase
import dev.passerby.data.mappers.MainMapper
import dev.passerby.data.models.dto.BookDto
import dev.passerby.data.models.dto.HotelDto
import dev.passerby.data.models.dto.RoomDto
import dev.passerby.data.network.ApiFactory
import dev.passerby.data.network.BaseResponse
import dev.passerby.domain.models.BookModel
import dev.passerby.domain.models.HotelModel
import dev.passerby.domain.models.RoomModel
import dev.passerby.domain.repos.MainRepository

class MainRepositoryImpl(application: Application) : MainRepository {

    private val db = AppDatabase.getInstance(application)
    private val bookInfoDao = db.bookInfoDao()
    private val hotelInfoDao = db.hotelInfoDao()
    private val roomInfoDao = db.roomsInfoDao()
    private val apiService = ApiFactory.apiService
    private val mainMapper = MainMapper()
    private val bookResult: MutableLiveData<BaseResponse<BookDto>> = MutableLiveData()
    private val hotelResult: MutableLiveData<BaseResponse<HotelDto>> = MutableLiveData()
    private val roomResult: MutableLiveData<BaseResponse<List<RoomDto>>> = MutableLiveData()

    override fun getBookInfo(): LiveData<BookModel> {
        val bookInfo = bookInfoDao.getBookInfo()
        return bookInfo.map { mainMapper.bookMapper.mapBookDbModelToEntity(it) }
    }

    override fun getHotelInfo(): LiveData<HotelModel> {
        val hotelInfo = hotelInfoDao.getHotelInfo()
        return hotelInfo.map { mainMapper.hotelMapper.mapHotelDbModelToEntity(it) }
    }

    override fun getRoomsInfo(): LiveData<List<RoomModel>> {
        val roomsInfo = roomInfoDao.getRoomsInfo()
        return roomsInfo.map { list ->
            list.map {
                mainMapper.roomMapper.mapRoomDbModelToEntity(it)
            }
        }
    }

    override suspend fun loadBookInfo() {
        bookResult.value = BaseResponse.Loading()
        try {
            val response = apiService.loadBookInfo()
            if (response.code() == 200) {
                bookResult.value = BaseResponse.Success(response.body())
                val dbModelList = mainMapper.bookMapper.mapBookDtoToDbModel(response.body()!!)
                bookInfoDao.insertBook(dbModelList)
                Log.d(TAG, "loadCoins: ${response.body()}")
            } else {
                bookResult.value = BaseResponse.Error(response.message())
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadCoins: $ex")
            bookResult.value = BaseResponse.Error(ex.message)
        }
    }

    override suspend fun loadHotelInfo() {
        hotelResult.value = BaseResponse.Loading()
        try {
            val response = apiService.loadHotelInfo()
            if (response.code() == 200) {
                hotelResult.value = BaseResponse.Success(response.body())
                val dbModelList =
                    mainMapper.hotelMapper.mapHotelDtoToDbModel(response.body()!!)
                hotelInfoDao.insertHotel(dbModelList)
                Log.d(TAG, "loadCoins: ${response.body()}")
            } else {
                hotelResult.value = BaseResponse.Error(response.message())
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadCoins: $ex")
            hotelResult.value = BaseResponse.Error(ex.message)
        }
    }

    override suspend fun loadRoomsInfo() {
        roomResult.value = BaseResponse.Loading()
        try {
            val response = apiService.loadRoomsInfo()
            if (response.code() == 200) {
                roomResult.value = BaseResponse.Success(response.body())
                val dbModelList =
                    response.body()?.map { mainMapper.roomMapper.mapRoomDtoToDbModel(it) }
                roomInfoDao.insertRooms(dbModelList ?: emptyList())
                Log.d(TAG, "loadCoins: ${response.body()?.get(0)}")
            } else {
                roomResult.value = BaseResponse.Error(response.message())
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadCoins: $ex")
            roomResult.value = BaseResponse.Error(ex.message)
        }
    }

    companion object {
        private const val TAG = "MainRepositoryImpl"
    }
}