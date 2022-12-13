package com.faldi.githubuserapp.view.detail_user
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.faldi.githubuserapp.R
import com.faldi.githubuserapp.data.api.request.Item
import com.faldi.githubuserapp.data.local.UserFavorite
import com.faldi.githubuserapp.databinding.ActivityDetailUserBinding
import com.faldi.githubuserapp.view.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailUserActivity : AppCompatActivity() {
    private var usernameDetail = false
    private lateinit var binding: ActivityDetailUserBinding
    private val viewModel by viewModel<DetailUserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.title = getString(R.string.detail_title)
        viewModel.loading.observe(this@DetailUserActivity){
            showLoading(it)
        }
        viewModel.view.observe(this@DetailUserActivity){
            showView(it)
        }
        setupTabLayout()
        val data = intent.getParcelableExtra<Item>("DATA")
        val dataFavorite = intent.getParcelableExtra<UserFavorite>("DATA FAVORITE")
        when{
            data?.login!=null->{
                check(data.login)
                addDeleteFavorite(uid = data.id, username = data.login, avatar = data.avatarUrl)
                viewModel.getUserDetail(data.login)
            }
            dataFavorite?.username!=null->{
                check(dataFavorite.username)
                addDeleteFavorite(uid = dataFavorite.uid, username = dataFavorite.username.toString(), avatar = dataFavorite.avatar_url)
                val name = dataFavorite.username
                viewModel.getUserDetail(name)

                Log.d("test data ",name)
            }
        }

        binding.apply {


            viewModel.detail.observe(this@DetailUserActivity){ data1 ->
                Glide.with(this@DetailUserActivity)
                    .load(data1.avatarUrl)
                    .circleCrop()
                    .into(avatarUserDetailIv)

                usernameTv.text = data1.login
                nameTv.text = data1.name
                locationTv2.text = data1.location
                companyTv.text = data1.company
                repositoryTv2.text = data1.publicRepos.toString()
                followerCount.text = data1.followers.toString()
                followingCount.text = data1.following.toString()


                shareButton.setOnClickListener {
                    val share = "Username   : ${data1.login}\n" +
                            "Name       : ${data1.name}\n" +
                            "Company    : ${data1.company}\n" +
                            "Location   : ${data1.location}\n" +
                            "Repository : ${data1.htmlUrl}"

                    val shareIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, share)
                        type= "text/plain"
                    }
                    val sendIntent = Intent.createChooser(shareIntent,null)
                    startActivity(sendIntent)
                }
            }
        }

    }
    private fun setupTabLayout(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailUserActivity)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)

       viewModel.detail.observe(this@DetailUserActivity){


           TabLayoutMediator(tabs, viewPager) { tab, position ->
               tab.text = resources.getString(TAB_TITLES[position])
           }.attach()
           supportActionBar?.elevation = 0f
       }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showView(isView: Boolean) {
        binding.constFrame.visibility = if (isView) View.VISIBLE else View.GONE
        binding.linearLayout.visibility = if (isView) View.VISIBLE else View.GONE
    }

    private fun addDeleteFavorite(uid:Int,username:String,avatar:String?){

        binding.apply {
            favoriteButton.setOnClickListener{
                if (!usernameDetail){
                    viewModel.addFavorite(
                        UserFavorite(
                            uid,
                            username,
                            avatar
                        )
                    )
                    binding.favoriteButton.setImageResource(R.drawable.ic_baseline_star_full)
                    Toast.makeText(this@DetailUserActivity,"telah ditambahkan",Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.deleteFavorite(
                        UserFavorite(
                            uid,
                            username,
                            avatar
                        )
                    )
                    binding.favoriteButton.setImageResource(R.drawable.ic_baseline_star_border)
                    Toast.makeText(this@DetailUserActivity,"telah dihapus",Toast.LENGTH_SHORT).show()
                }


            }
        }
        check(username)
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private fun check(username: String?){
        viewModel.getFavoriteByUsername(username)
        viewModel.checkFavorite.observe(this){
            if(it!=null){
                if (it.username==username){
                    usernameDetail = true
                    binding.favoriteButton.setImageResource(R.drawable.ic_baseline_star_full)
                }else{
                    usernameDetail = false
                    binding.favoriteButton.setImageResource(R.drawable.ic_baseline_star_border)
                }
            }


        }
    }





}