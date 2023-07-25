package com.umc.one_person_households_platform.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.CommunityCategoryAdapter
import com.umc.one_person_households_platform.databinding.FragmentCommunityBinding
import com.umc.one_person_households_platform.model.CommunityPostlistDTO


class CommunityFragment : Fragment() {


    private lateinit var
            binding : FragmentCommunityBinding
    lateinit var postAdapter: CommunityCategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_community,container,false)

        var latestbtn = binding.rbLatest
        var popularbtn = binding.rbPopular
        var delicious = binding.rbDelicious
        var question = binding.rbQuestion
        var communicate = binding.rbCommunicate

        // 테스트 데이터
        var postList : ArrayList<CommunityPostlistDTO> = arrayListOf()
        var firstauthor = "aaaa"
        var firstcategory = "최신글"
        var firstname = "최신글 테스트 _111"
        var latest_1  = CommunityPostlistDTO(firstcategory,firstname,firstauthor)
        var secondauthor = "bbbb"
        var secondcategory = "최신글"
        var secondname = "최신글 테스트 2222"
        var latest_2 = CommunityPostlistDTO(secondcategory,secondname,secondauthor)

        postList.add(latest_1)
        postList.add(latest_2)

        postAdapter = CommunityCategoryAdapter(postList)
        binding.rvPostlist.adapter = postAdapter
        binding.rvPostlist.layoutManager = LinearLayoutManager(requireContext())

        latestbtn.setOnClickListener {

            // 테스트 데이터
            var postList : ArrayList<CommunityPostlistDTO> = arrayListOf()
            var firstauthor = "aaaa"
            var firstcategory = "최신글"
            var firstname = "최신글 테스트 _111"
            var latest_1  = CommunityPostlistDTO(firstcategory,firstname,firstauthor)
            var secondauthor = "aaaa"
            var secondcategory = "최신글"
            var secondname = "최신글 테스트 _111"
            var latest_2 = CommunityPostlistDTO(secondcategory,secondname,secondauthor)

            postList.add(latest_1)
            postList.add(latest_2)

            postAdapter = CommunityCategoryAdapter(postList)
            binding.rvPostlist.adapter = postAdapter
            binding.rvPostlist.layoutManager = LinearLayoutManager(requireContext())




        }
        popularbtn.setOnClickListener {

            // 테스트 데이터
            var postList_2 : ArrayList<CommunityPostlistDTO> = arrayListOf()
            var firstauthor_2 = "aaaa"
            var firstcategory_2 = "인기글"
            var firstname_2 = "인기글 테스트 _111"
            var pupular_1  = CommunityPostlistDTO(firstcategory_2,firstname_2,firstauthor_2)
            var secondauthor_2 = "bbbb"
            var secondcategory_2 = "인기글"
            var secondname_2 = "인기글 테스트 _111"
            var pupular_2 = CommunityPostlistDTO(secondcategory_2,secondname_2,secondauthor_2)

            postList_2.add(pupular_1)
            postList_2.add(pupular_2)

            postAdapter = CommunityCategoryAdapter(postList_2)
            binding.rvPostlist.adapter = postAdapter
            binding.rvPostlist.layoutManager = LinearLayoutManager(requireContext())


        }
        delicious.setOnClickListener {

        }

        question.setOnClickListener {

        }
        communicate.setOnClickListener {

        }






        return binding.root
    }


}