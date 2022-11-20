package com.beta.orange.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.beta.orange.repository.MainActivityRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(private val mainActivityRepository: MainActivityRepository):ViewModel() {

    val liveData = MutableLiveData<Any>()

    fun request(){
        mainActivityRepository.request()
        viewModelScope.launch {
            mainActivityRepository.request().collect{
                liveData.postValue(it)
            }
        }
    }

    fun request2() = mainActivityRepository.request2().asLiveData()
}