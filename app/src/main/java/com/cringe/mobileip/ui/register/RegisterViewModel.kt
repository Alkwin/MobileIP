package com.cringe.mobileip.ui.register

import androidx.lifecycle.ViewModel
import com.cringe.mobileip.R
import com.cringe.mobileip.server.AuthenticationManager
import com.cringe.mobileip.ui.data.Result
import com.cringe.mobileip.ui.data.model.User
import com.cringe.mobileip.ui.login.LoggedInUserView
import com.cringe.mobileip.ui.login.LoginResult

class RegisterViewModel(private val authenticationManager: AuthenticationManager): ViewModel() {
    fun register(
        name: String,
        email: String,
        password: String
    ) {
        val result = authenticationManager.register(name, email, password)
    }
}