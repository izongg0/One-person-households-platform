package com.umc.one_person_households_platform.repository.groupbuyingdetail

import android.util.Log
import com.umc.one_person_households_platform.model.GroupBuyingDetailContent

class GroupBuyingDetailRepository(private val groupBuyingDetailRemoteDataSource: GroupBuyingDetailRemoteDataSource) {

    private lateinit var groupBuyingDetailData: GroupBuyingDetailContent

    // 공동 구매 상세 정보 반환
    suspend fun getGroupBuyingDetailData(postIdx: Int): GroupBuyingDetailContent {
        try {
            val response = groupBuyingDetailRemoteDataSource.getGroupBuyingDetailData(postIdx)
            if (response.isSuccessful){
                groupBuyingDetailData = response.body()!!.groupBuyingDetailContent
            }else {
                Log.d("errorCode","오류 코드: ${response.code()}")
            }
        }catch (e: Exception){
            Log.e("errorCode", e.toString())
        }

        return groupBuyingDetailData
    }
}