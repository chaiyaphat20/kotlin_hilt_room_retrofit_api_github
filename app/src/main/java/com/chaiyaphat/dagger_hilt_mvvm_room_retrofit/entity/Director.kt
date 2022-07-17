package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "director_table")
@Entity
data class Director(
    @PrimaryKey(autoGenerate = false)
    val directorName: String,
    val schoolName: String
)

