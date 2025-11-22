package com.chat.viewmodelactivity

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var numbar=0
    fun add(){
        numbar++
    }
}