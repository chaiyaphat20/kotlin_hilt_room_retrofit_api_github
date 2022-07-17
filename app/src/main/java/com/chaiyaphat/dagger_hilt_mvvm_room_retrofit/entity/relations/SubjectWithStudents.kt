package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Student
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Subject

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectName",  //pk of Subject (1)
        entityColumn = "studentName",  //pk of Students (2)
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)

