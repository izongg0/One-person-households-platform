package com.umc.one_person_households_platform.view.groupbuyingwrite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupBuyingWriteShareViewModel : ViewModel() {

    private val _groupBuyingCategory = MutableLiveData<String>()
    val groupBuyingCategory: LiveData<String> = _groupBuyingCategory

    private val _groupBuyingCount = MutableLiveData<String>()
    val groupBuyingCount: LiveData<String> = _groupBuyingCount

    init {
        setInit()
    }

    fun setGroupBuyingCategory(category: String) {
        _groupBuyingCategory.value = category
    }

    fun setGroupBuyingCount(count: Int) {
        _groupBuyingCount.value = count.toString()
    }

    fun setInit() {
        _groupBuyingCategory.value = "공동구매 할 상품의 카테고리를 선택해주세요"
        _groupBuyingCount.value = "-"
    }

    fun getGroupBuyingCategory(): Boolean {
        return groupBuyingCategory.value != "공동구매 할 상품의 카테고리를 선택해주세요"
    }

    fun getGroupBuyingCount(): Boolean {
        return groupBuyingCount.value != "-"
    }
}