package com.cringe.mobileip.ui.register

import android.util.Patterns
import androidx.lifecycle.*
import com.cringe.mobileip.R
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.server.model.register.RegisterUserData
import com.cringe.mobileip.ui.register.utils.RegisterFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegisterViewModel(private val authenticationManager: AuthenticationManager): ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val requestLiveData = MutableLiveData<RegisterUserData>()

    val answerLiveData = requestLiveData.switchMap {
        flow{
            emit(authenticationManager.register(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    fun register(futureUser: RegisterUserData) {
        requestLiveData.postValue(
            futureUser
        )
    }

    fun registerDataChanged(rUD: RegisterUserData) {
        if(!isEmailValid(rUD.email)) {
            _registerForm.value = RegisterFormState(emailError = R.string.invalid_email)
        } else if(!isPasswordValid(rUD.password1)) {
            _registerForm.value = RegisterFormState(password1Error = R.string.invalid_password)
        } else if(!isPasswordValid(rUD.password2)) {
            _registerForm.value = RegisterFormState(password2Error = R.string.invalid_password)
        } else if(rUD.password1 != rUD.password2) {
            _registerForm.value = RegisterFormState(password2Error = R.string.password_not_match)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
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