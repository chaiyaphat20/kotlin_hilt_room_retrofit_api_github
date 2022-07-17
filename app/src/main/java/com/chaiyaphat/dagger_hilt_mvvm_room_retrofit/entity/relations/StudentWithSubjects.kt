package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Student
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Subject

//Embedded ใช้กับ ชื่อตัวแรกของ data class
data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentName",  //pk of Student (1)
        entityColumn = "subjectName",  //pk of Subject  (2)
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subject: List<Subject>
)

//get query student ออกมา แต่ได้ subject เป็น List
