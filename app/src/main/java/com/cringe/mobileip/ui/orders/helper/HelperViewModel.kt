package com.cringe.mobileip.ui.orders.helper

import androidx.lifecycle.*
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.data.managers.HelperManager
import com.cringe.mobileip.server.model.helper.order.CheckOrderRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HelperViewModel: ViewModel() {
    private val helperManager = HelperManager()

    var currentOrderRequest = MutableLiveData<CheckOrderRequest>()

    var currentOrder = currentOrderRequest.switchMap {
        flow {
            emit(helperManager.checkOrder(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    fun startRequesting() {
        viewModelScope.launch {
            while(true) {
                currentOrderRequest.postValue(CheckOrderRequest(AuthenticationManager.userName))
                delay(5000)
            }
        }
    }
}