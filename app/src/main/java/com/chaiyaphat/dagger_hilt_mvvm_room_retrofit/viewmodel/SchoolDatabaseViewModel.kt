package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Director
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.School
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Student
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Subject
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations.StudentSubjectCrossRef
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.network.SchoolDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDatabaseViewModel @Inject constructor(private val schoolDatabaseRepository: SchoolDatabaseRepository) :
    ViewModel() {
    fun insertSchool(school: School) {
        viewModelScope.launch {
            schoolDatabaseRepository.insertSchool(school)
        }
    }

    fun insertDirector(director: Director) {
        viewModelScope.launch {
            schoolDatabaseRepository.insertDirector(director)
        }
    }

    fun insertStudent(student: Student) {
        viewModelScope.launch {
            schoolDatabaseRepository.insertStudent(student)
        }
    }

    fun insertSubject(subject: Subject) {
        viewModelScope.launch {
            schoolDatabaseRepository.insertSubject(subject)
        }
    }

    fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef) {
        viewModelScope.launch {
            schoolDatabaseRepository.insertStudentSubjectCrossRef(crossRef)
        }
    }

    fun getSchoolAndDirectorWithSchoolName(schoolName: String) {
        viewModelScope.launch {
            schoolDatabaseRepository.getSchoolAndDirectorWithSchoolName(schoolName)
        }
    }

    fun getSchoolWithStudents(schoolName: String) {
        viewModelScope.launch {
            schoolDatabaseRepository.getSchoolWithStudents(schoolName)
        }
    }

    fun getStudentsOfSubject(subjectName: String) {
        viewModelScope.launch {
            schoolDatabaseRepository.getStudentsOfSubject(subjectName)
        }
    }

    fun getSubjectsOfStudent(studentName: String) {
        viewModelScope.launch {
            schoolDatabaseRepository.getSubjectsOfStudent(studentName)
        }
    }


}