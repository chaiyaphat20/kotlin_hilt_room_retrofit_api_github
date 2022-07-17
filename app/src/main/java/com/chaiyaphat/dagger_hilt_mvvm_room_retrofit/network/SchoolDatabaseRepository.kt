package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.network

import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.db.SchoolDao
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Director
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.School
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Student
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Subject
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations.StudentSubjectCrossRef
import javax.inject.Inject

class SchoolDatabaseRepository @Inject constructor(private val schoolDao: SchoolDao) {
    suspend fun insertSchool(school: School) {
        schoolDao.insertSchool(school)
    }

    suspend fun insertDirector(director: Director) {
        schoolDao.insertDirector(director)
    }

    suspend fun insertStudent(student: Student) {
        schoolDao.insertStudent(student)
    }

    suspend fun insertSubject(subject: Subject) {
        schoolDao.insertSubject(subject)
    }

    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef) {
        schoolDao.insertStudentSubjectCrossRef(crossRef)
    }

    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String) {
        schoolDao.getSchoolAndDirectorWithSchoolName(schoolName)
    }

    suspend fun getSchoolWithStudents(schoolName: String) {
        schoolDao.getSchoolWithStudents(schoolName)
    }

    suspend fun getStudentsOfSubject(subjectName: String) {
        schoolDao.getStudentsOfSubject(subjectName)
    }

    suspend fun getSubjectsOfStudent(studentName: String) {
        schoolDao.getSubjectsOfStudent(studentName)
    }
}