package com.umc.one_person_households_platform.repository.groupBuyingWrite

import android.util.Log
import com.umc.one_person_households_platform.model.GroupBuyingWritePost

class GroupBuyingWriteRepository(private val groupBuyingWriteRemoteDataSource: GroupBuyingWriteRemoteDataSource) {

    private var groupBuyingWriteData = true

    suspend fun addGroupBuyingWriteData(groupBuyingWritePost: GroupBuyingWritePost): Boolean {
        try {
            val response = groupBuyingWriteRemoteDataSource.addGroupBuyingWriteData(groupBuyingWritePost)
            if (response.isSuccessful) {
                groupBuyingWriteData = response.body()!!.isSuccess == "true"
            } else {
                Log.d("errorCode", "오류 코드: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("errorCode", e.toString())
        }

        return groupBuyingWriteData
    }
}