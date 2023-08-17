package com.umc.one_person_households_platform.repository.groupbuyingsearch

import com.umc.one_person_households_platform.model.GroupBuying
import retrofit2.Response

interface GroupBuyingSearchDataSource {

    suspend fun getGroupBuyingSearchData(query: String, startIdx: Int): Response<GroupBuying>
}