package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class School(
    @PrimaryKey(autoGenerate = false)
    val schoolName: String
)

