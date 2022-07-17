package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = false)
    val subjectName: String
)
