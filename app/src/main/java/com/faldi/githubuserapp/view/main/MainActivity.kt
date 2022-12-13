package com.faldi.githubuserapp.view.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faldi.githubuserapp.R
import com.faldi.githubuserapp.UserListAdapter
import com.faldi.githubuserapp.data.api.request.Item
import com.faldi.githubuserapp.databinding.ActivityMainBinding
import com.faldi.githubuserapp.view.detail_user.DetailUserActivity
import com.faldi.githubuserapp.view.favorite.FavoriteActivity
import com.faldi.githubuserapp.view.setting.SettingActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var rvMain : RecyclerView
    private val viewModel by viewModel<MainViewModel>()
    private var doubleBackToExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)


        viewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }

        viewModel.loading.observe(this@MainActivity){load->
            showLoading(load)
        }
        binding.apply {
            nullText.setText(R.string.welcome_text)
            nullText.visibility = View.VISIBLE
        }
        setContentView(binding.root)
        setupAdapter()
        doubleBackExit()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.app_bar, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchQuery.observe(this@MainActivity) {

                    if(it.isEmpty()){
                        binding.apply {
                            nullText.setText(R.string.user_null)
                            rvMain.visibility = View.INVISIBLE
                            nullText.visibility = View.VISIBLE
                        }

                    }else if(query.isEmpty()){
                        binding.nullText.setText(R.string.empty_query)
                    }else{
                        binding.nullText.visibility = View.GONE
                        rvMain.visibility = View.VISIBLE
                        showRvList(it)
                    }

                }
                viewModel.getName(query)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.setting->{
                val intentToSetting = Intent(this@MainActivity,SettingActivity::class.java)
                startActivity(intentToSetting)
            }
            R.id.favorite->{
                val intentToFavorite = Intent(this@MainActivity,FavoriteActivity::class.java)
                startActivity(intentToFavorite)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupAdapter(){
        rvMain=binding.mainRv
        rvMain.setHasFixedSize(true)
        rvMain.layoutManager =LinearLayoutManager(this)
    }


    private fun showRvList(list : List<Item>){
        val userListAdapter = UserListAdapter(list)
        rvMain.adapter = userListAdapter

        userListAdapter.setOnItemClickCallback(object : UserListAdapter.OnItemClickCallback {

            override fun onItemClicked(data: Item) {
                val intentToDetail = Intent(this@MainActivity, DetailUserActivity::class.java)
                intentToDetail.putExtra("DATA",data)
                startActivity(intentToDetail)
            }

        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }



    private fun doubleBackExit() = onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            when {
                doubleBackToExit -> {
                    finish()
                }
                else -> {
                    doubleBackToExit = true
                    rvMain.visibility = View.INVISIBLE
                    binding.apply {
                        nullText.setText(R.string.welcome_text)
                        nullText.visibility = View.VISIBLE
                    }
                    Toast.makeText(applicationContext, getString(R.string.back_again), Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        kotlin.run {
                            doubleBackToExit = false
                        }
                    }, 2000)
                }
            }
        }
    })


}