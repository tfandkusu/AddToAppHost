package com.tfandkusu.addtoapphost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object FlutterHandler {
    private val _mainFlutterFragmentAttach =  MutableLiveData(true)
    val mainFlutterFragmentAttach: LiveData<Boolean> = _mainFlutterFragmentAttach

    fun onCreateFlutterActivity() {
        _mainFlutterFragmentAttach.value = false
    }

    fun onDestroyFlutterActivity() {
        _mainFlutterFragmentAttach.value = true
    }
}