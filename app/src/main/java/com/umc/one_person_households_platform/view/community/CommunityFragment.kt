package com.umc.one_person_households_platform.view.community

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.CommunityCategoryAdapter
import com.umc.one_person_households_platform.databinding.FragmentCommunityBinding
import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommunityFragment : Fragment() {


    private lateinit var
            binding: FragmentCommunityBinding
    lateinit var postAdapter: CommunityCategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)


        var latestbtn = binding.rbLatest
        var popularbtn = binding.rbPopular
        var delicious = binding.rbDelicious
        var question = binding.rbQuestion
        var communicate = binding.rbCommunicate
        var share = binding.rbShare
//        var selectedCategory = "최신순"

        callApiSort("최신순")

//        when (selectedCategory) {
//            "최신글" -> {
//                binding.rbLatest.isChecked = true
//                callApiSort("최신순")
//
//            }
//            "인기글" -> {
//
//                binding.rbPopular.isChecked = true
//                callApiSort("인기글")
//
//            }
//            "맛집이야기" -> {
//                binding.rbDelicious.isChecked = true
//                callApi("맛집이야기")
//
//            }
//            "질문있어요" -> {
//
//                binding.rbQuestion.isChecked = true
//                callApi("질문있어요")
//
//            }
//            "대화해요" -> {
//
//                binding.rbCommunicate.isChecked = true
//                callApi("대화해요")
//
//            }
//            "공유해요" -> {
//
//                binding.rbShare.isChecked = true
//                callApi("공유해요")
//
//            }
//        }

        latestbtn.setOnClickListener {
//            selectedCategory = "최신순"
            callApiSort("최신순")
        }
        popularbtn.setOnClickListener {
//            selectedCategory = "인기순"
            callApiSort("인기순")

        }
        delicious.setOnClickListener {
//            selectedCategory = "맛집이야기"
            callApi("맛집이야기")

        }

        question.setOnClickListener {
//            selectedCategory = "질문있어요"
            callApi("질문있어요")

        }
        communicate.setOnClickListener {
//            selectedCategory = "대화해요"
            callApi("대화해요")

        }

        share.setOnClickListener {
//            selectedCategory = "공유해요"
            callApi("공유해요")

        }

        binding.btnNewpost.setOnClickListener {

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_communityFragment_to_newpostFragment)

        }


        binding.etSearchArea.setOnClickListener {

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_communityFragment_to_searchFragment)

        }
//

        return binding.root
    }


    fun callApiSort(sort: String) {

        val apiClient = ApiClient.create()

        apiClient.getCommunitySort(sort).enqueue(object : Callback<CommunityDTO> {
            override fun onResponse(call: Call<CommunityDTO>, response: Response<CommunityDTO>) {
                if (response.isSuccessful) {
                    var postlist = response.body()

                    Log.d("fltmxm", postlist.toString())
                    if (postlist != null) {

                        postAdapter = CommunityCategoryAdapter(postlist!!)
                        binding.rvPostlist.adapter = postAdapter
                        binding.rvPostlist.layoutManager = LinearLayoutManager(requireContext())

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


    fun callApi(category: String) {

        val apiClient = ApiClient.create()

        apiClient.getCommunity(category, 0).enqueue(object : Callback<CommunityDTO> {
            override fun onResponse(call: Call<CommunityDTO>, response: Response<CommunityDTO>) {
                if (response.isSuccessful) {
                    var postlist = response.body()

                    Log.d("fltmxm", postlist.toString())
                    if (postlist != null) {

                        postAdapter = CommunityCategoryAdapter(postlist!!)
                        binding.rvPostlist.adapter = postAdapter
                        binding.rvPostlist.layoutManager = LinearLayoutManager(requireContext())

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


