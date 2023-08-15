package com.umc.one_person_households_platform.repository.groupbuying

import com.umc.one_person_households_platform.model.GroupBuying
import retrofit2.Response

interface GroupBuyingDataSource {

    suspend fun getGroupBuyingRecentData(startIdx: Int): Response<GroupBuying>

    suspend fun getGroupBuyingImminentData(startIdx: Int): Response<GroupBuying>

    suspend fun getGroupBuyingIngredients(startIdx: Int): Response<GroupBuying>

    suspend fun getGroupBuyingHouseholdGoods(startIdx: Int): Response<GroupBuying>

    suspend fun getGroupBuyingETC(startIdx: Int): Response<GroupBuying>
}