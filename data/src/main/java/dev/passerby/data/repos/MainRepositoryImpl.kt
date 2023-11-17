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
import dev.passerby.data.models.dto.RoomListDto
import dev.passerby.data.network.ApiService
import dev.passerby.data.network.BaseResponse
import dev.passerby.domain.models.BookModel
import dev.passerby.domain.models.HotelModel
import dev.passerby.domain.models.RoomModel
import dev.passerby.domain.repos.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    application: Application
) : MainRepository {

    private val db = AppDatabase.getInstance(application)
    private val bookInfoDao = db.bookInfoDao()
    private val hotelInfoDao = db.hotelInfoDao()
    private val roomInfoDao = db.roomsInfoDao()

    private val mainMapper = MainMapper()

    override fun getBookInfo(): LiveData<List<BookModel>> {
        val bookInfo = bookInfoDao.getBookInfo()
        return bookInfo.map { list ->
            list.map {
                mainMapper.bookMapper.mapBookDbModelToEntity(it)
            }
        }
    }

    override fun getHotelInfo(): LiveData<List<HotelModel>> {
        val hotelInfo = hotelInfoDao.getHotelInfo()
        return hotelInfo.map { list ->
            list.map {
                mainMapper.hotelMapper.mapHotelDbModelToEntity(it)
            }
        }
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
        val bookResult: MutableLiveData<BaseResponse<BookDto>> = MutableLiveData()

        bookResult.value = BaseResponse.Loading()
        try {
            val response = apiService.loadBookInfo()
            if (response.code() == 200) {
                bookResult.value = BaseResponse.Success(response.body())
                val dbModelList = mainMapper.bookMapper.mapBookDtoToDbModel(response.body()!!)
                bookInfoDao.insertBook(dbModelList)
                Log.d(TAG, "loadBookInfoTry: ${response.body()}")
            } else {
                bookResult.value = BaseResponse.Error(response.message())
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadBookInfoCatch: $ex")
            bookResult.value = BaseResponse.Error(ex.message)
        }
    }

    override suspend fun loadHotelInfo() {
        val hotelResult: MutableLiveData<BaseResponse<HotelDto>> = MutableLiveData()

        hotelResult.value = BaseResponse.Loading()
        try {
            val response = apiService.loadHotelInfo()
            if (response.code() == 200) {
                hotelResult.value = BaseResponse.Success(response.body())
                val dbModelList = mainMapper.hotelMapper.mapHotelDtoToDbModel(response.body()!!)
                hotelInfoDao.insertHotel(dbModelList)
                Log.d(TAG, "loadHotelInfoTry: ${response.body()}")
            } else {
                hotelResult.value = BaseResponse.Error(response.message())
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadHotelInfoCatch: $ex")
            hotelResult.value = BaseResponse.Error(ex.message)
        }
    }

    override suspend fun loadRoomsInfo() {
        val roomResult: MutableLiveData<BaseResponse<RoomListDto>> = MutableLiveData()

        roomResult.value = BaseResponse.Loading()
        try {
            val response = apiService.loadRoomsInfo()
            if (response.code() == 200) {
                roomResult.value = BaseResponse.Success(response.body())
                val dbModelList =
                    response.body()?.rooms?.map { mainMapper.roomMapper.mapRoomDtoToDbModel(it) }
                roomInfoDao.insertRooms(dbModelList ?: emptyList())
                Log.d(TAG, "loadRoomsInfoTry: ${response.body()?.rooms?.get(0)}")
            } else {
                roomResult.value = BaseResponse.Error(response.message())
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadRoomsInfoCatch: $ex")
            roomResult.value = BaseResponse.Error(ex.message)
        }
    }

    companion object {
        private const val TAG = "MainRepositoryImpl"
    }
}