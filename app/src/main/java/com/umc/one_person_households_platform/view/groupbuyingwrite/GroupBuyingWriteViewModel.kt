package com.umc.one_person_households_platform.view.groupbuyingwrite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.one_person_households_platform.model.GroupBuyingWritePost
import com.umc.one_person_households_platform.repository.groupBuyingWrite.GroupBuyingWriteRepository
import kotlinx.coroutines.launch

class GroupBuyingWriteViewModel(private val groupBuyingWriteRepository: GroupBuyingWriteRepository) : ViewModel() {

    private val _groupBuyingWrite = MutableLiveData<Boolean>()
    val groupBuyingWrite: LiveData<Boolean> = _groupBuyingWrite

    private val _groupBuyingPhoto = MutableLiveData<List<String>>()
    val groupBuyingPhoto: LiveData<List<String>> = _groupBuyingPhoto

    init {
        _groupBuyingPhoto.value = listOf()
    }

    fun addGroupBuyingPhoto(photo: String) {
        _groupBuyingPhoto.value = getGroupBuyingPhoto().apply { add(photo) }
    }

    fun removePhoto(position: Int) {
        _groupBuyingPhoto.value = getGroupBuyingPhoto().apply { removeAt(position) }
    }

    fun getGroupBuyingPhoto(): MutableList<String> {
        return _groupBuyingPhoto.value?.toMutableList() ?: mutableListOf()
    }

    fun addGroupBuyingWriteData(groupBuyingWritePost: GroupBuyingWritePost) {
        viewModelScope.launch {
            val getGroupBuyingWrite = groupBuyingWriteRepository.addGroupBuyingWriteData(groupBuyingWritePost)
            _groupBuyingWrite.value = getGroupBuyingWrite
        }
    }
}