package com.faldi.githubuserapp.view.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faldi.githubuserapp.R
import com.faldi.githubuserapp.data.local.UserFavorite
import com.faldi.githubuserapp.databinding.ActivityFavoriteBinding
import com.faldi.githubuserapp.view.detail_user.DetailUserActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var rvMain : RecyclerView
    private val viewModel by viewModel<FavoriteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.title= getString(R.string.favorite_title)
        showRv()
        setupAdapter()

    }
    private fun showRv(){
        viewModel.getFavorite.observe(this){
            binding.apply {
                progressBarFavorite.visibility = View.VISIBLE
                if(it.isEmpty()){
                    progressBarFavorite.visibility = View.GONE
                    rvMain.visibility = View.GONE
                    noUserTv.text = getString(R.string.no_favorite_user)
                    noUserTv.visibility = View.VISIBLE
                }else{
                    progressBarFavorite.visibility = View.GONE
                    noUserTv.visibility = View.GONE
                    showRvList(it)
                    rvMain.visibility = View.VISIBLE
                }

            }

        }
    }
    private fun setupAdapter(){
        rvMain=binding.listFavorite
        rvMain.setHasFixedSize(true)
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    private fun showRvList(list : List<UserFavorite>){
        val userListAdapter = FavoriteAdapter(list)
        rvMain.adapter = userListAdapter

        userListAdapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {

            override fun onItemClicked(data: UserFavorite) {
                val intentToDetail = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                intentToDetail.putExtra("DATA FAVORITE",data)
                startActivity(intentToDetail)
            }

        })
    }

    override fun onResume() {
        super.onResume()
        showRv()
    }
}