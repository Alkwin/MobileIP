package com.cringe.mobileip.ui.orders.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cringe.mobileip.server.model.utils.Order
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperViewModel: ViewModel() {
    var currentOrder: Order? = null

    fun startRequesting() {
        viewModelScope.launch {
            while(true) {
                //TODO continue implementation
                currentOrder = null
                delay(5000)
            }
        }
    }
}