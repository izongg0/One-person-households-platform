package com.umc.one_person_households_platform.repository.groupbuyingdetail

import com.umc.one_person_households_platform.model.GroupBuyingDetail
import retrofit2.Response

interface GroupBuyingDetailDataSource {

    suspend fun getGroupBuyingDetailData(postIdx: Int): Response<GroupBuyingDetail>
}