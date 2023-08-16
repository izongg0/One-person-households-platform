package com.umc.one_person_households_platform.repository.groupbuyingsearch

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.umc.one_person_households_platform.model.GroupBuyingContent

class GroupBuyingSearchPagingSource(
    private val groupBuyingSearchRemoteDataSource: GroupBuyingSearchRemoteDataSource,
    private val query: String
) : PagingSource<Int, GroupBuyingContent>() {

    override fun getRefreshKey(state: PagingState<Int, GroupBuyingContent>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(15) ?: anchorPage?.nextKey?.minus(15)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GroupBuyingContent> {
        return try {
            val next = params.key ?: 0
            val response = groupBuyingSearchRemoteDataSource.getGroupBuyingSearchData(query, next).body()!!.groupBuyingContent

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = next + 15
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}