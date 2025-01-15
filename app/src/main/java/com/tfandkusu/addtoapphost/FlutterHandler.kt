package com.tfandkusu.addtoapphost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object FlutterHandler {
    private val _mainFlutterFragmentAttach =  MutableLiveData(true)
    val mainFlutterFragmentAttach: LiveData<Boolean> = _mainFlutterFragmentAttach

    fun onCallFlutterActivity() {
        _mainFlutterFragmentAttach.value = false
    }

    fun onCloseFlutterActivity() {
        _mainFlutterFragmentAttach.value = true
    }
}