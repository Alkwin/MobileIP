package com.cringe.mobileip.ui.register.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.data.managers.LoginManager
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.data.managers.RegisterManager
import com.cringe.mobileip.ui.register.RegisterViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class RegisterViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                authenticationManager = AuthenticationManager(
                    loginManager = LoginManager(),
                    registerManager = RegisterManager()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}