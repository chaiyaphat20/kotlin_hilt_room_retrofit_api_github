package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.db.AppDao
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoriesList
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoryData
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(
    private val retroServiceInterface: RetroServiceInterface,
    private val appDao: AppDao
) {
    //pattern add data
    private val _apiResponseLiveData = MutableLiveData<NetworkResult<RepositoriesList>>()
    val apiResponseLiveData: LiveData<NetworkResult<RepositoriesList>>
        get() = _apiResponseLiveData
    //Pattern

    fun getAllRecords(): LiveData<List<RepositoryData>> {
        Log.d("TAG_ART", "makeApiCall3")
        return appDao.getAllRecords()
    }

//    fun insertRecord(repositoryData: RepositoryData) {
//        appDao.insertRecord(repositoryData)
//    }

    //get data from api
    suspend fun makeApiCall(query: String?) {
        val response = retroServiceInterface.getDateFromAPI(query!!)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<RepositoriesList>) {
        if (response.isSuccessful && response.body() != null) {
            _apiResponseLiveData.postValue(NetworkResult.Success(response.body()!!)) //กรณี success จะ add response เข้าไป _userResponseLiveData
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _apiResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message"))) //กรณี error จะ add error message เข้าไป _userResponseLiveData
        } else {
            _apiResponseLiveData.postValue(NetworkResult.Error("Some thing went wrong.")) //กรณีอื่นๆจะ add message เข้าไป
        }
    }
}