package com.example.taskproject.offlineStorage.carlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.taskproject.offlineStorage.data.CarListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    repository: CarListRepository
):ViewModel(){
    val cars=repository.getCars().asLiveData()
}