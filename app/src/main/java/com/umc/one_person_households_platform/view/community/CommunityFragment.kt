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
import com.umc.one_person_households_platform.model.CommunityPost
import com.umc.one_person_households_platform.model.CommunityPostlistDTO
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


        callApi("최신글")

        latestbtn.setOnClickListener {
            callApi("최신글")
        }
        popularbtn.setOnClickListener {
            callApi("인기글")

        }
        delicious.setOnClickListener {
            callApi("맛집이야기")

        }

        question.setOnClickListener {
            callApi("질문있어요")

        }
        communicate.setOnClickListener {
            callApi("대화해요")

        }

        share.setOnClickListener {
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


        return binding.root
    }


    fun callApi(category: String) {
        var postlist: CommunityDTO? = null

        val apiClient = ApiClient.create()

        apiClient.getCommunity(category, 0).enqueue(object : Callback<CommunityDTO> {
            override fun onResponse(call: Call<CommunityDTO>, response: Response<CommunityDTO>) {
                if (response.isSuccessful) {
                    postlist = response.body()

                    if (postlist != null) {

                        postAdapter = CommunityCategoryAdapter(postlist!!)
                        binding.rvPostlist.adapter = postAdapter
                        binding.rvPostlist.layoutManager = LinearLayoutManager(requireContext())

                    }


                    // items를 RecyclerView Adapter에 전달하거나 다른 처리를 진행합니다.
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