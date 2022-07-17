package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations

import androidx.room.Entity

@Entity(primaryKeys = ["studentName", "subjectName"])
data class StudentSubjectCrossRef(
    val studentName: String,  //pk of student
    val subjectName: String  //pk of subject
)

