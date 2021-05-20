package com.cringe.mobileip.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.cringe.mobileip.R
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.server.model.login.LoginAnswer
import com.cringe.mobileip.server.model.login.LoginResult
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.server.model.utils.User
import com.cringe.mobileip.ui.login.utils.LoggedInUserView
import com.cringe.mobileip.ui.login.utils.LoginFormState

class LoginViewModel(private val authenticationManager: AuthenticationManager) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<Result<LoginAnswer>>()
    val loginResult: LiveData<Result<LoginAnswer>> = _loginResult

    fun login(email: String, password: String) {
        val result = authenticationManager.login(User(email, password))
        _loginResult.value = result
    }

    fun loginDataChanged(email: String, password: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 3
    }
}