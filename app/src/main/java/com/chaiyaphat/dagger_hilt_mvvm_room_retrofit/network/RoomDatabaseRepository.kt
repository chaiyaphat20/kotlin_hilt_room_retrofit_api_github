package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.network

import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.db.AppDao
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoryData
import javax.inject.Inject

class RoomDatabaseRepository @Inject constructor(
    private val appDao: AppDao
) {
    fun insertRecord(repositoryData: RepositoryData) {
        appDao.insertRecord(repositoryData)
    }

    fun deleteRecordAll() {
        appDao.deleteAllRecordsO()
    }
}