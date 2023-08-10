package com.umc.one_person_households_platform.repository.home

import com.umc.one_person_households_platform.model.Community
import com.umc.one_person_households_platform.model.GroupBuying
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Response

class HomeRemoteDataSource(private val apiClient: ApiClient): HomeDataSource {

    override suspend fun getCommunityCategories(): Response<Community> {
        return apiClient.getCommunityCategories()
    }

    override suspend fun getGroupBuyingCategories(): Response<GroupBuying> {
        return apiClient.getGroupBuyingCategories()
    }
}