package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoryData

@Dao
interface AppDao {
    @Query("SELECT * FROM repositories")
    fun getAllRecords(): LiveData<List<RepositoryData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(repositoryData: RepositoryData)

    @Query("DELETE FROM repositories")
    fun deleteAllRecordsO()
}