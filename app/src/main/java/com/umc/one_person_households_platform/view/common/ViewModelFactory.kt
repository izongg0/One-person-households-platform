package com.umc.one_person_households_platform.view.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umc.one_person_households_platform.network.ApiClient
import com.umc.one_person_households_platform.repository.groupBuyingWrite.GroupBuyingWriteRemoteDataSource
import com.umc.one_person_households_platform.repository.groupBuyingWrite.GroupBuyingWriteRepository
import com.umc.one_person_households_platform.repository.groupbuying.GroupBuyingRemoteDataSource
import com.umc.one_person_households_platform.repository.groupbuying.GroupBuyingRepository
import com.umc.one_person_households_platform.repository.groupbuyingdetail.GroupBuyingDetailRemoteDataSource
import com.umc.one_person_households_platform.repository.groupbuyingdetail.GroupBuyingDetailRepository
import com.umc.one_person_households_platform.repository.groupbuyingsearch.GroupBuyingSearchRemoteDataSource
import com.umc.one_person_households_platform.repository.groupbuyingsearch.GroupBuyingSearchRepository
import com.umc.one_person_households_platform.repository.home.HomeRemoteDataSource
import com.umc.one_person_households_platform.repository.home.HomeRepository
import com.umc.one_person_households_platform.view.groupbuying.GroupBuyingViewModel
import com.umc.one_person_households_platform.view.groupbuyingdetail.GroupBuyingDetailViewModel
import com.umc.one_person_households_platform.view.groupbuyingsearch.GroupBuyingSearchViewModel
import com.umc.one_person_households_platform.view.groupbuyingwrite.GroupBuyingWriteViewModel
import com.umc.one_person_households_platform.view.home.HomeViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeRemoteDataSource(ApiClient.create()))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(GroupBuyingViewModel::class.java) -> {
                val repository = GroupBuyingRepository(GroupBuyingRemoteDataSource(ApiClient.create()))
                GroupBuyingViewModel(repository) as T
            }
            modelClass.isAssignableFrom(GroupBuyingSearchViewModel::class.java) -> {
                val repository = GroupBuyingSearchRepository(GroupBuyingSearchRemoteDataSource(ApiClient.create()))
                GroupBuyingSearchViewModel(repository) as T
            }
            modelClass.isAssignableFrom(GroupBuyingDetailViewModel::class.java) -> {
                val repository = GroupBuyingDetailRepository(GroupBuyingDetailRemoteDataSource(ApiClient.create()))
                GroupBuyingDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(GroupBuyingWriteViewModel::class.java) -> {
                val repository = GroupBuyingWriteRepository(GroupBuyingWriteRemoteDataSource(ApiClient.create()))
                GroupBuyingWriteViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}