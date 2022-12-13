package com.faldi.githubuserapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faldi.githubuserapp.data.api.request.FollowDataClassItem
import com.faldi.githubuserapp.databinding.UserItemBinding


class FollowAdapter: RecyclerView.Adapter<FollowAdapter.ListViewHolder1>() {
    private val listFollow = ArrayList<FollowDataClassItem>()

    fun setUser (item : ArrayList<FollowDataClassItem>){
        listFollow.clear()
        listFollow.addAll(item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder1 {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder1(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder1, position: Int) {
        val user = listFollow[position]
        holder.binding.apply {
            username.text = user.login
            Glide.with(holder.itemView.context)
                .load(user.avatarUrl)
                .circleCrop()
                .into(avatarIv)
        }
    }
    override fun getItemCount(): Int = listFollow.size
    class ListViewHolder1(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)
}