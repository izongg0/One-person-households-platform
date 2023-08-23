package com.umc.one_person_households_platform.repository.groupBuyingWrite

import com.umc.one_person_households_platform.model.GroupBuyingWrite
import com.umc.one_person_households_platform.model.GroupBuyingWritePost
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Response

class GroupBuyingWriteRemoteDataSource(private val apiClient: ApiClient) : GroupBuyingWriteDataSource {
    override suspend fun addGroupBuyingWriteData(groupBuyingWritePost: GroupBuyingWritePost): Response<GroupBuyingWrite> {
        return apiClient.addGroupBuyingWrite(groupBuyingWritePost)
    }
}