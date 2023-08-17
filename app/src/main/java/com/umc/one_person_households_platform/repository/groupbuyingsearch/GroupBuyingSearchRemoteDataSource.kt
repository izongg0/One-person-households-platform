package com.umc.one_person_households_platform.repository.groupbuyingsearch

import com.umc.one_person_households_platform.model.GroupBuying
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Response

class GroupBuyingSearchRemoteDataSource(private val apiClient: ApiClient) : GroupBuyingSearchDataSource {

    override suspend fun getGroupBuyingSearchData(query: String, startIdx: Int): Response<GroupBuying> {
        return apiClient.getGroupBuyingSearch(query, startIdx, 15)
    }
}