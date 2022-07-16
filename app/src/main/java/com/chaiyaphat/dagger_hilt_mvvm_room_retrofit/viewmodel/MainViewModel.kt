package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoriesList
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoryData
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.network.RetroRepository
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val retroRepository: RetroRepository) :
    ViewModel() {
    //pattern add data
    val apiResponseData: LiveData<NetworkResult<RepositoriesList>>
        get() = retroRepository.apiResponseLiveData
    //Pattern

    fun getAllRepositoryList(): LiveData<List<RepositoryData>> {
        return retroRepository.getAllRecords()
    }

    fun makeApiCall() {
        viewModelScope.launch {
            retroRepository.makeApiCall("ny")
        }
    }
}