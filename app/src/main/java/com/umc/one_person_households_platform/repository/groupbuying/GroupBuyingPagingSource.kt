package com.umc.one_person_households_platform.repository.groupbuying

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.umc.one_person_households_platform.model.GroupBuyingContent

class GroupBuyingPagingSource(
    private val groupBuyingRemoteDataSource: GroupBuyingRemoteDataSource,
    private val category: String
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
            var response = emptyList<GroupBuyingContent>()

            when(category) {
                "최신순" -> response = groupBuyingRemoteDataSource.getGroupBuyingRecentData(next).body()!!.groupBuyingContent
                "마감임박순" -> response = groupBuyingRemoteDataSource.getGroupBuyingImminentData(next).body()!!.groupBuyingContent
                "식재료" -> response = groupBuyingRemoteDataSource.getGroupBuyingIngredients(next).body()!!.groupBuyingContent
                "생활용품" -> response = groupBuyingRemoteDataSource.getGroupBuyingHouseholdGoods(next).body()!!.groupBuyingContent
                "기타" -> response = groupBuyingRemoteDataSource.getGroupBuyingETC(next).body()!!.groupBuyingContent
            }

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