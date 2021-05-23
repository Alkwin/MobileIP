package com.cringe.mobileip.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.server.model.register.RegisterAnswer
import com.cringe.mobileip.server.model.register.RegisterUserData
import com.cringe.mobileip.server.model.utils.Result

class RegisterViewModel(private val authenticationManager: AuthenticationManager): ViewModel() {

    private val _registerResult = MutableLiveData<Result<RegisterAnswer>>()
    val registerResult: LiveData<Result<RegisterAnswer>> = _registerResult

    fun register(futureUser: RegisterUserData) {
        val result = authenticationManager.register(futureUser)

        _registerResult.value = result
    }
}