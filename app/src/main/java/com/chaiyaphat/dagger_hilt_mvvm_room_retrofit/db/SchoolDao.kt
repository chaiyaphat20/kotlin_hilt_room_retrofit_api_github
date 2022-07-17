package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.db

import androidx.room.*
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Director
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.School
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Student
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Subject
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations.*

//ให้ school เป็นตัวหลัก director ตัวรอง
@Dao
interface SchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirector>

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolWithStudents(schoolName: String): List<SchoolWithStudents>

    @Transaction
    @Query("SELECT * FROM subject WHERE subjectName = :subjectName")
    suspend fun getStudentsOfSubject(subjectName: String): List<SubjectWithStudents>

    @Transaction
    @Query("SELECT * FROM student WHERE studentName = :studentName")
    suspend fun getSubjectsOfStudent(studentName: String): List<StudentWithSubjects>
}