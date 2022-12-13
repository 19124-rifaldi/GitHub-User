package com.faldi.githubuserapp.view.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.faldi.githubuserapp.data.local.UserFavorite
import com.faldi.githubuserapp.databinding.UserItemBinding

class FavoriteAdapter(private val listUser: List<UserFavorite>): RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {
    private lateinit var clickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.clickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        holder.binding.apply {
            username.text = user.username
            Glide.with(holder.itemView.context)
                .load(user.avatar_url)
                .circleCrop()
                .into(avatarIv)

            holder.itemView.setOnClickListener { clickCallback.onItemClicked(listUser[holder.adapterPosition]) }

        }
    }

    override fun getItemCount() : Int =listUser.size

    class ListViewHolder(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: UserFavorite)
    }
}