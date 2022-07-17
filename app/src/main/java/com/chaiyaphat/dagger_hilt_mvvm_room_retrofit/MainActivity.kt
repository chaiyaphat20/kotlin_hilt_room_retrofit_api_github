package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.adapter.RecycleAdapter
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.databinding.ActivityMainBinding
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Director
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.School
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Student
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Subject
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations.StudentSubjectCrossRef
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.viewmodel.MainViewModel
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.viewmodel.RoomDatabaseViewModel
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.viewmodel.SchoolDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val bindings: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerViewAdapter: RecycleAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var roomDbModel: RoomDatabaseViewModel
    private lateinit var schoolDbModel: SchoolDatabaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        roomDbModel = ViewModelProvider(this)[RoomDatabaseViewModel::class.java]
        schoolDbModel = ViewModelProvider(this)[SchoolDatabaseViewModel::class.java]

        schoolDb()

        initViewModel()
        initMainViewModel()

        bindings.btnCallApi.setOnClickListener {
            Toast.makeText(this, "Call", Toast.LENGTH_SHORT).show()
            viewModel.makeApiCall()
        }
    }

    private fun initViewModel() {
        bindings.rv1.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecycleAdapter()
            adapter = recyclerViewAdapter
        }
    }

    private fun initMainViewModel() {
        viewModel.getAllRepositoryList()
        viewModel.apiResponseData.observe(this, Observer {
            recyclerViewAdapter.setListData(it.data?.items)
            roomDbModel.deleteRecordAll()
            it.data?.items?.forEach { value ->
                roomDbModel.insertRecord(value)  //call DB
            }
        })
    }

    private fun schoolDb() {
        //>>add database
        val directors = listOf(
            Director("Mike Litoris", "Jake Wharton School"),
            Director("Jack Goff", "Kotlin School"),
            Director("Chris P. Chicken", "JetBrains School")
        )
        val schools = listOf(
            School("Jake Wharton School"),
            School("Kotlin School"),
            School("JetBrains School")
        )
        val subjects = listOf(
            Subject("Dating for programmers"),
            Subject("Avoiding depression"),
            Subject("Bug Fix Meditation"),
            Subject("Logcat for Newbies"),
            Subject("How to use Google")
        )
        val students = listOf(
            Student("Beff Jezos", 2, "Kotlin School"),
            Student("Mark Suckerberg", 5, "Jake Wharton School"),
            Student("Gill Bates", 8, "Kotlin School"),
            Student("Donny Jepp", 1, "Kotlin School"),
            Student("Hom Tanks", 2, "JetBrains School")
        )
        val studentSubjectRelations = listOf(
            StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
            StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
            StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
            StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
            StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
            StudentSubjectCrossRef("Gill Bates", "How to use Google"),
            StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
            StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
            StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
        )
        lifecycleScope.launch {
            directors.forEach { schoolDbModel.insertDirector(it) }
            schools.forEach { schoolDbModel.insertSchool(it) }
            subjects.forEach { schoolDbModel.insertSubject(it) }
            students.forEach { schoolDbModel.insertStudent(it) }
            studentSubjectRelations.forEach { schoolDbModel.insertStudentSubjectCrossRef(it) }
            val schoolWithDirector =
                schoolDbModel.getSchoolAndDirectorWithSchoolName("Kotlin School")
            val getStudentsOfSubject = schoolDbModel.getStudentsOfSubject("Dating for programmers")
//            Log.d("testest", schoolWithDirector.toString())
//            Log.d("testest", getStudentsOfSubject.toString())
        }
    }
}