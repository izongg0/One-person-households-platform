package com.umc.one_person_households_platform.repository.groupbuying

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.umc.one_person_households_platform.model.GroupBuyingContent
import kotlinx.coroutines.flow.Flow

class GroupBuyingRepository(private val groupBuyingRemoteDataSource: GroupBuyingRemoteDataSource) {

    // 공동 구매 화면 최신 공구 탭 반환
    fun getGroupBuyingRecentData(): Flow<PagingData<GroupBuyingContent>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            pagingSourceFactory = { GroupBuyingPagingSource(groupBuyingRemoteDataSource, "최신순") }
        ).flow
    }

    // 공동 구매 화면 마감 임박 공구 탭 반환
    fun getGroupBuyingImminentData(): Flow<PagingData<GroupBuyingContent>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            pagingSourceFactory = { GroupBuyingPagingSource(groupBuyingRemoteDataSource, "마감임박순") }
        ).flow
    }

    // 공동 구매 화면 식재료 탭 반환
    fun getGroupBuyingIngredientsData(): Flow<PagingData<GroupBuyingContent>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            pagingSourceFactory = { GroupBuyingPagingSource(groupBuyingRemoteDataSource, "식재료") }
        ).flow
    }

    // 공동 구매 화면 생활 용품 탭 반환
    fun getGroupBuyingHouseholdGoodsData(): Flow<PagingData<GroupBuyingContent>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            pagingSourceFactory = { GroupBuyingPagingSource(groupBuyingRemoteDataSource, "생활용품") }
        ).flow
    }

    // 공동 구매 화면 기타 탭 반환
    fun getGroupBuyingETCData(): Flow<PagingData<GroupBuyingContent>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            pagingSourceFactory = { GroupBuyingPagingSource(groupBuyingRemoteDataSource, "기타") }
        ).flow
    }
}