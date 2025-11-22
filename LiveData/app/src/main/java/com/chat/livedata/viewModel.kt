package com.chat.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class viewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>  = _count

    fun increaseCount() {
        _count.value = (_count.value ?: 0) + 1
    }


}