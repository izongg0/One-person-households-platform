package com.umc.one_person_households_platform.repository.groupBuyingWrite

import com.umc.one_person_households_platform.model.GroupBuyingWrite
import com.umc.one_person_households_platform.model.GroupBuyingWritePost
import retrofit2.Response

interface GroupBuyingWriteDataSource {

    suspend fun addGroupBuyingWriteData(groupBuyingWritePost: GroupBuyingWritePost): Response<GroupBuyingWrite>
}