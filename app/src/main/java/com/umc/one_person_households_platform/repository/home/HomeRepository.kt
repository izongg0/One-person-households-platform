package com.umc.one_person_households_platform.repository.home

import android.util.Log
import com.umc.one_person_households_platform.model.GroupBuyingContent

class HomeRepository(private val homeRemoteDataSource: HomeRemoteDataSource) {

    private var groupBuyingContentList: List<GroupBuyingContent> = listOf()

    suspend fun getGroupBuyingCategories(): List<GroupBuyingContent> {
        try {
            val response = homeRemoteDataSource.getGroupBuyingCategories()
            if (response.isSuccessful){
                groupBuyingContentList = response.body()!!.GroupBuyingContent
            }else {
                Log.d("groupBuying","오류 코드: ${response.code()}")
            }
        }catch (e: Exception){
            Log.e("groupBuying", e.toString())
        }

        return groupBuyingContentList
    }
}