package com.umc.one_person_households_platform.repository.home

import android.util.Log
import com.umc.one_person_households_platform.model.CommunityContent
import com.umc.one_person_households_platform.model.GroupBuyingContent

class HomeRepository(private val homeRemoteDataSource: HomeRemoteDataSource) {

    private var communityContentList: List<CommunityContent> = listOf()
    private var groupBuyingContentList: List<GroupBuyingContent> = listOf()

    // 홈 화면 마감 임박 공구 리스트 반환
    suspend fun getGroupBuyingCategories(): List<GroupBuyingContent> {
        try {
            val response = homeRemoteDataSource.getGroupBuyingCategories()
            if (response.isSuccessful){
                groupBuyingContentList = response.body()!!.GroupBuyingContent
            }else {
                Log.d("home","오류 코드: ${response.code()}")
            }
        }catch (e: Exception){
            Log.e("home", e.toString())
        }

        return groupBuyingContentList
    }

    // 홈 화면 커뮤니티 인기글 리스트 반환
    suspend fun getCommunityCategories(): List<CommunityContent> {
        try {
            val response = homeRemoteDataSource.getCommunityCategories()
            if (response.isSuccessful) {
                communityContentList = response.body()!!.communityContent
            }else {
                Log.d("home","오류 코드: ${response.code()}")
            }
        }catch (e: Exception){
            Log.e("home", e.toString())
        }

        return communityContentList
    }
}