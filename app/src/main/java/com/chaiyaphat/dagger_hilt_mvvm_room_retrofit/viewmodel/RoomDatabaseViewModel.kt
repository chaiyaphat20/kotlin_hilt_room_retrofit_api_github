package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoryData
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.network.RoomDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDatabaseViewModel @Inject constructor(private val roomDatabaseRepository: RoomDatabaseRepository) :
    ViewModel() {
    fun insertRecord(repositoryData: RepositoryData) {
        viewModelScope.launch {
            roomDatabaseRepository.insertRecord(repositoryData)
        }
    }

    fun deleteRecordAll() {
        viewModelScope.launch {
            roomDatabaseRepository.deleteRecordAll()
        }
    }
}