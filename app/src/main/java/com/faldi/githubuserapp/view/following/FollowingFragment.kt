package com.faldi.githubuserapp.view.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.faldi.githubuserapp.data.api.request.Item
import com.faldi.githubuserapp.data.local.UserFavorite
import com.faldi.githubuserapp.databinding.FragmentFollowingBinding
import com.faldi.githubuserapp.view.FollowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FollowingViewModel>()
    private val followAdapter : FollowAdapter by lazy {
        FollowAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = activity?.intent?.getParcelableExtra<Item>("DATA")
        val dataFavorite = activity?.intent?.getParcelableExtra<UserFavorite>("DATA FAVORITE")



        when{
            data?.login!=null->{
                showRv(data?.login.toString())
            }
            dataFavorite?.username!=null->{
                showRv(dataFavorite?.username.toString())
            }
        }


    }

    private fun setupAdapter(){
        binding.apply {
            followingRv.layoutManager  = LinearLayoutManager(requireContext())
            followingRv.adapter = followAdapter
            followingRv.setHasFixedSize(true)
        }
    }

    private fun showRv(username : String){
        viewModel.getUsername(username)
        viewModel.username.observe(viewLifecycleOwner){
            followAdapter.setUser(it)
            setupAdapter()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}