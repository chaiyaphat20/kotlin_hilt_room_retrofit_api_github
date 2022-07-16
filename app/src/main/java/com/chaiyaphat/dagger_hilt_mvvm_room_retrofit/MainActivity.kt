package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.adapter.RecycleAdapter
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.databinding.ActivityMainBinding
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.viewmodel.MainViewModel
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.viewmodel.RoomDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val bindings: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerViewAdapter: RecycleAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var roomDbModel: RoomDatabaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        roomDbModel = ViewModelProvider(this)[RoomDatabaseViewModel::class.java]

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
}