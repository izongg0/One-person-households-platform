package com.umc.one_person_households_platform.view.groupbuyingdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.one_person_households_platform.model.GroupBuyingDetailContent
import com.umc.one_person_households_platform.repository.groupbuyingdetail.GroupBuyingDetailRepository
import kotlinx.coroutines.launch

class GroupBuyingDetailViewModel(private val groupBuyingDetailRepository: GroupBuyingDetailRepository): ViewModel() {

    private val _content = MutableLiveData<GroupBuyingDetailContent>()
    val content: LiveData<GroupBuyingDetailContent> = _content

    fun loadContent(postIdx: Int) {
        viewModelScope.launch {
            val getGroupBuyingDetailContent = groupBuyingDetailRepository.getGroupBuyingDetailData(postIdx)
            _content.value = getGroupBuyingDetailContent
        }
    }
}