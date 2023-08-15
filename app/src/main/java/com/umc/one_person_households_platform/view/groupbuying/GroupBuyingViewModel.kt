package com.umc.one_person_households_platform.view.groupbuying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.umc.one_person_households_platform.model.GroupBuyingContent
import com.umc.one_person_households_platform.repository.groupbuying.GroupBuyingRepository
import kotlinx.coroutines.flow.Flow

class GroupBuyingViewModel(private val groupBuyingRepository: GroupBuyingRepository) : ViewModel() {

    lateinit var content: Flow<PagingData<GroupBuyingContent>>

    fun getGroupBuyingRecentData() {
        content = groupBuyingRepository.getGroupBuyingRecentData().cachedIn(viewModelScope)
    }

    fun getGroupBuyingImminentData() {
        content = groupBuyingRepository.getGroupBuyingImminentData().cachedIn(viewModelScope)
    }

    fun getGroupBuyingIngredientsData() {
        content = groupBuyingRepository.getGroupBuyingIngredientsData().cachedIn(viewModelScope)
    }

    fun getGroupBuyingHouseholdGoodsData() {
        content = groupBuyingRepository.getGroupBuyingHouseholdGoodsData().cachedIn(viewModelScope)
    }

    fun getGroupBuyingETCData() {
        content = groupBuyingRepository.getGroupBuyingETCData().cachedIn(viewModelScope)
    }
}