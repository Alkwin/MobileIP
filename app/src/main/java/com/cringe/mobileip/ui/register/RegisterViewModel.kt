package com.cringe.mobileip.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.server.model.utils.Result

class RegisterViewModel(private val authenticationManager: AuthenticationManager): ViewModel() {

    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean> = _registerResult

    fun register(
        name: String,
        email: String,
        password: String
    ) {
        val result = authenticationManager.register(name, email, password)
        _registerResult.value = result is Result.Success
    }
}