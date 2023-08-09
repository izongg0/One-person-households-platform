package com.umc.one_person_households_platform.view.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umc.one_person_households_platform.network.ApiClient
import com.umc.one_person_households_platform.repository.home.HomeRemoteDataSource
import com.umc.one_person_households_platform.repository.home.HomeRepository
import com.umc.one_person_households_platform.view.home.HomeViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeRemoteDataSource(ApiClient.create()))
                HomeViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}