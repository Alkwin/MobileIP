package com.cringe.mobileip.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.ui.data.LoginManager
import com.cringe.mobileip.server.AuthenticationManager
import com.cringe.mobileip.ui.data.RegisterManager

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                authenticationManager = AuthenticationManager(
                    loginManager = LoginManager(),
                    registerManager = RegisterManager()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}