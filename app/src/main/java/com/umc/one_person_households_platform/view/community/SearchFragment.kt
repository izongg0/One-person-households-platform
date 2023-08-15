package com.umc.one_person_households_platform.view.community

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.CommunityCategoryAdapter
import com.umc.one_person_households_platform.databinding.FragmentSearchBinding
import com.umc.one_person_households_platform.model.CommunityDTO

import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {


    lateinit var postAdapter: CommunityCategoryAdapter

    private lateinit var
            binding: FragmentSearchBinding //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false) //


        binding.ivArrow.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_searchFragment_to_communityFragment)

        }

        binding.etSearchArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchPosts(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return binding.root //
    }

    private fun searchPosts(keyword: String) {

        val apiClient = ApiClient.create()
        var filteredPosts: CommunityDTO? = null

        apiClient.getCommunitySearch(keyword).enqueue(object : Callback<CommunityDTO> {
            override fun onResponse(call: Call<CommunityDTO>, response: Response<CommunityDTO>) {
                if (response.isSuccessful) {
                    filteredPosts = response.body()
                    Log.d("rufrhk", filteredPosts.toString())

                    if (filteredPosts?.result != null) {
                        binding.tvDescription.isVisible = false
                        binding.rvSearch.isVisible = true

                        postAdapter = CommunityCategoryAdapter(filteredPosts!!)
                        binding.rvSearch.adapter = postAdapter
                        binding.rvSearch.layoutManager = LinearLayoutManager(requireContext())

                    } else {
                        binding.tvDescription.isVisible = true
                        binding.rvSearch.isVisible = false

                    }
                } else {
                    Log.e("ApiError", "API 요청 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CommunityDTO>, t: Throwable) {
                Log.e("ApiError", "API 요청 실패", t)
            }
        })


    }


}