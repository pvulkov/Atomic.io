package com.atomic.io.sportwidget.common.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch


inline fun ViewModel.launchInCurrentScope(crossinline action1: Action1) {
    this.viewModelScope.launch {
        action1.invoke()
    }
}

fun ViewModel.launchInScope(dispatcher: CoroutineDispatcher, action1: Action1) {
    this.viewModelScope.launch(dispatcher) {
        action1.invoke()
    }
}
