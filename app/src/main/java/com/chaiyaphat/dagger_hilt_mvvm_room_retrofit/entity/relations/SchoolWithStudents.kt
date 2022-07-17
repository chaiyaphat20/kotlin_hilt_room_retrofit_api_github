package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.School
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName", //pk of school
        entityColumn = "schoolName" //fk of Student
    )
    val student: List<Student>
)

