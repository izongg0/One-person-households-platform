package com.umc.one_person_households_platform.view.groupbuyingsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.umc.one_person_households_platform.model.GroupBuyingContent
import com.umc.one_person_households_platform.repository.groupbuyingsearch.GroupBuyingSearchRepository
import kotlinx.coroutines.flow.Flow

class GroupBuyingSearchViewModel(private val groupBuyingSearchRepository: GroupBuyingSearchRepository) : ViewModel() {

    lateinit var content: Flow<PagingData<GroupBuyingContent>>

    fun getGroupBuyingSearchData(query: String) {
        content = groupBuyingSearchRepository.getGroupBuyingSearchData(query).cachedIn(viewModelScope)
    }
}