package com.umc.one_person_households_platform.repository.groupbuyingdetail

import com.umc.one_person_households_platform.model.GroupBuyingDetail
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Response

class GroupBuyingDetailRemoteDataSource(private val apiClient: ApiClient): GroupBuyingDetailDataSource {

    override suspend fun getGroupBuyingDetailData(postIdx: Int): Response<GroupBuyingDetail> {
        return apiClient.getGroupBuyingDetail(postIdx)
    }
}