package com.example.enfecapplication.mainScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enfecapplication.databinding.RecyclerLayoutBinding
import com.example.enfecapplication.mainScreen.model.PostData


class PostAdapter(private var postList: List<PostData>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = postList.size

    inner class PostViewHolder(private val binding: RecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostData) {


            binding.txttitle.text = item.title.toString()
            binding.txtsubtitle.text = item.body.toString()

            binding.txtCompanyName.text = item.company!!.name.toString()
            binding.txtCompanyPhrase.text = item.company!!.catchPhrase.toString()
            binding.txtCompanyBs.text = item.company!!.bs.toString()

            binding.txtlat.text = "Lats :"+item.geo!!.lat.toString()
            binding.txtlong.text = "Longs :"+item.geo!!.lng.toString()
            binding.txtId.text = item.uID.toString()
        }
    }



}