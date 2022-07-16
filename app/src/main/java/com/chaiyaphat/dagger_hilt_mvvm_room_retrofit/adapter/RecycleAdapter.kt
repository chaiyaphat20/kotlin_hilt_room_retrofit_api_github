package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.databinding.RepositoryListBinding
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoryData

class RecycleAdapter() : RecyclerView.Adapter<RecycleAdapter.MyViewHolder>() {

    private var listData: List<RepositoryData>? = null
    fun setListData(lisData: List<RepositoryData>?) {
        this.listData = lisData
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: RepositoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val view = RepositoryListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return MyViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listData!![position]
        with(holder) {
            with(data) {
                binding.tvName.text = name
                binding.tvDesc.text = description
                Glide.with(binding.imageAvatar).load(owner?.avatar_url).into(binding.imageAvatar)
            }
        }
    }

    override fun getItemCount(): Int {
        if(listData == null) return  0
        return listData?.size!!
    }
}