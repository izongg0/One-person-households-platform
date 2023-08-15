package com.umc.one_person_households_platform.repository.groupbuying

import com.umc.one_person_households_platform.model.GroupBuying
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Response

class GroupBuyingRemoteDataSource(private val apiClient: ApiClient): GroupBuyingDataSource {

    override suspend fun getGroupBuyingRecentData(startIdx: Int): Response<GroupBuying> {
        return apiClient.getGroupBuyingSort("최신순", startIdx, 15)
    }

    override suspend fun getGroupBuyingImminentData(startIdx: Int): Response<GroupBuying> {
        return apiClient.getGroupBuyingSort("마감임박순", startIdx, 15)
    }

    override suspend fun getGroupBuyingIngredients(startIdx: Int): Response<GroupBuying> {
        return apiClient.getGroupBuyingCategory("식재료", startIdx, 15)
    }

    override suspend fun getGroupBuyingHouseholdGoods(startIdx: Int): Response<GroupBuying> {
        return apiClient.getGroupBuyingCategory("생활용품", startIdx, 15)
    }

    override suspend fun getGroupBuyingETC(startIdx: Int): Response<GroupBuying> {
        return apiClient.getGroupBuyingCategory("기타", startIdx, 15)
    }
}