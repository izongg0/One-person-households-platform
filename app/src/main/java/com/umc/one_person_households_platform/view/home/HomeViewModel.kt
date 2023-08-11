package com.umc.one_person_households_platform.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.one_person_households_platform.model.CommunityContent
import com.umc.one_person_households_platform.model.GroupBuyingContent
import com.umc.one_person_households_platform.model.HotRecipeContent
import com.umc.one_person_households_platform.repository.home.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _communityContent = MutableLiveData<List<CommunityContent>>()
    val communityContent: LiveData<List<CommunityContent>> = _communityContent

    private val _groupBuyingContent = MutableLiveData<List<GroupBuyingContent>>()
    val groupBuyingContent: LiveData<List<GroupBuyingContent>> = _groupBuyingContent

    private val _hotRecipeContent = MutableLiveData<List<HotRecipeContent>>()
    val hotRecipeContent: LiveData<List<HotRecipeContent>> = _hotRecipeContent

    init {
        loadContent()
    }

    private fun loadContent() {
        viewModelScope.launch {
            val getGroupBuyingContent = homeRepository.getGroupBuyingCategories()
            _groupBuyingContent.value = getGroupBuyingContent

            val getCommunityContent = homeRepository.getCommunityCategories()
            _communityContent.value = getCommunityContent

            val getHotRecipeContent = homeRepository.getHotRecipeCategory()
            _hotRecipeContent.value = getHotRecipeContent
        }
    }
}