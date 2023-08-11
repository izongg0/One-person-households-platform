package com.umc.one_person_households_platform.repository.home

import android.util.Log
import com.umc.one_person_households_platform.model.CommunityContent
import com.umc.one_person_households_platform.model.GroupBuyingContent
import com.umc.one_person_households_platform.model.HotRecipeContent

class HomeRepository(private val homeRemoteDataSource: HomeRemoteDataSource) {

    private var communityContentList: List<CommunityContent> = listOf()
    private var groupBuyingContentList: List<GroupBuyingContent> = listOf()
    private var hotRecipeContent: List<HotRecipeContent> = listOf()

    // 홈 화면 마감 임박 공구 리스트 반환
    suspend fun getGroupBuyingCategories(): List<GroupBuyingContent> {
        try {
            val response = homeRemoteDataSource.getGroupBuyingCategories()
            if (response.isSuccessful){
                groupBuyingContentList = response.body()!!.GroupBuyingContent
            }else {
                Log.d("errorCode","오류 코드: ${response.code()}")
            }
        }catch (e: Exception){
            Log.e("errorCode", e.toString())
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
                Log.d("errorCode","오류 코드: ${response.code()}")
            }
        }catch (e: Exception){
            Log.e("errorCode", e.toString())
        }

        return communityContentList
    }

    // 홈 화면 금주 HOT 레시피 반환
    suspend fun getHotRecipeCategory(): List<HotRecipeContent> {
        try {
            val response = homeRemoteDataSource.getHotRecipeCategory()
            if (response.isSuccessful) {
                hotRecipeContent = response.body()!!.hotRecipeContent
            }else {
                Log.d("errorCode","오류 코드: ${response.code()}")
            }
        }catch (e: Exception){
            Log.e("errorCode", e.toString())
        }

        return hotRecipeContent
    }
}