package com.cringe.mobileip.ui.login

import android.util.Patterns
import androidx.lifecycle.*
import com.cringe.mobileip.R
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.server.model.login.LoginAnswer
import com.cringe.mobileip.server.model.login.LoginResult
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.server.model.utils.User
import com.cringe.mobileip.ui.login.utils.LoggedInUserView
import com.cringe.mobileip.ui.login.utils.LoginFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginViewModel(private val authenticationManager: AuthenticationManager) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val requestLiveData = MutableLiveData<User>()

    val answerLiveData = requestLiveData.switchMap {
        flow {
            emit(authenticationManager.login(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    fun login(email: String, password: String) {
        requestLiveData.postValue(
            User(email, password)
        )
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