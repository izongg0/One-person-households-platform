package com.umc.one_person_households_platform.view.community

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.CommunitySearchAdapter
import com.umc.one_person_households_platform.databinding.FragmentSearchBinding
import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.model.CommunityPostItems
import com.umc.one_person_households_platform.model.CommunityPostlistDTO
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {


    private val allPosts :CommunityDTO? = null // 전체 게시글 목록
    private val filteredPosts = mutableListOf<CommunityPostItems>() // 검색 결과 게시글 목록
    private val adapter = CommunitySearchAdapter()

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




//        val apiClient = ApiClient.create()
//        val searchQuery = "your_search_query_here"
//
//        val call = apiClient.getCommunitySearch(searchQuery)
//        call.enqueue(object : Callback<CommunityDTO> {
//            override fun onResponse(call: Call<CommunityDTO>, response: Response<CommunityDTO>) {
//                // 성공적으로 응답을 받았을 때 처리
//            }
//
//            override fun onFailure(call: Call<CommunityDTO>, t: Throwable) {
//                // 요청이 실패했을 때 처리
//            }
//        })

//        val apiClient = ApiClient.create()
//
//        apiClient.getCommunity().enqueue(object : Callback<CommunityDTO> {
//            override fun onResponse(call: Call<CommunityDTO>, response: Response<CommunityDTO>) {
//                if (response.isSuccessful) {
//                    val allPosts = response.body()
//                    // TODO: 데이터 출력 또는 처리
//                } else {
//                    // TODO: 오류 처리
//                }
//            }
//
//            override fun onFailure(call: Call<CommunityDTO>, t: Throwable) {
//                // TODO: 네트워크 오류 처리
//            }
//        })



        // 테스트 데이터 (없으면 recyclerview null Exception 나옴)
        // api 형식에 따라 데이터 형식 바꾸기
//        allPosts.add(CommunityPostlistDTO("최신글", "첫번째 게시글", "내용1"))
//        allPosts.add(CommunityPostlistDTO("인기글", "두번째 게시글", "내용2"))

        binding.rvSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearch.adapter = adapter

        binding.ivArrow.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_searchFragment_to_communityFragment)

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
        filteredPosts!!.clear()

//        for (post in allPosts!!.result) {
//            if (post.title.contains(keyword, ignoreCase = true) || post.nickname.contains(
//                    keyword,
//                    ignoreCase = true
//                )
//            ) {
//                filteredPosts!!.add(post)
//            }
//        }
        adapter.submitList(filteredPosts)
    }


}