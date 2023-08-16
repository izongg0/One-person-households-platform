package com.umc.one_person_households_platform.repository.groupbuyingsearch

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.umc.one_person_households_platform.model.GroupBuyingContent
import kotlinx.coroutines.flow.Flow

class GroupBuyingSearchRepository(private val groupBuyingSearchRemoteDataSource: GroupBuyingSearchRemoteDataSource) {

    fun getGroupBuyingSearchData(query: String): Flow<PagingData<GroupBuyingContent>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            pagingSourceFactory = { GroupBuyingSearchPagingSource(groupBuyingSearchRemoteDataSource, query) }
        ).flow
    }
}