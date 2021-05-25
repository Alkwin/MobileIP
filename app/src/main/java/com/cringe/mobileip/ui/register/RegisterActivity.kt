package com.cringe.mobileip.ui.register

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.databinding.ActivityRegisterBinding
import com.cringe.mobileip.server.model.register.RegisterUserData
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.ui.register.utils.RegisterViewModelFactory
import com.cringe.mobileip.utils.afterTextChanged

class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.name
        val surname = binding.surname
        val userName = binding.username
        val email = binding.email
        val password1 = binding.password1
        val password2 = binding.password2
        val address = binding.address
        val phoneNumber = binding.phoneNumber
        val isolated = true
        val maxDistanceAccepted = binding.maxDistance
        val startHour = binding.startHour
        val finalHour = binding.finalHour
        val register = binding.register
        val loading = binding.loading

        val errorMessage = binding.errorMessage

        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory())
            .get(RegisterViewModel::class.java)

        binding.register.setOnClickListener {
            loading.visibility = View.VISIBLE
            registerViewModel.register(
                getRegisterUserData()
            )
        }

        binding.email.afterTextChanged {
            registerViewModel.registerDataChanged(
                getRegisterUserData()
            )
        }

        binding.password1.afterTextChanged {
            registerViewModel.registerDataChanged(
                getRegisterUserData()
            )
        }

        binding.password2.afterTextChanged {
            registerViewModel.registerDataChanged(
                getRegisterUserData()
            )
        }

        registerViewModel.registerFormState.observe(this@RegisterActivity, Observer {
            val registerState = it ?: return@Observer
            register.isEnabled = it.isDataValid
            if(registerState.emailError != null) {
                email.error = getString(registerState.emailError)
            }
            if(registerState.password1Error != null) {
                password1.error = getString(registerState.password1Error)
            }
            if(registerState.password2Error != null) {
                password2.error = getString(registerState.password2Error)
            }
        })

        registerViewModel.answerLiveData.observe(this@RegisterActivity, Observer {
            loading.visibility = View.INVISIBLE
            when(it) {
                is Result.Success -> {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
                is Result.Failure -> {
                    errorMessage.setText(it.data.message)
                }
                is Result.Exception -> {
                    errorMessage.setText(it.exception.message)
                }
            }
        })
    }

    private fun getRegisterUserData() =
        RegisterUserData(
            name = binding.name.text.toString(),
            surname = binding.surname.text.toString(),
            userName = binding.username.text.toString(),
            email = binding.email.text.toString(),
            password1 = binding.password1.text.toString(),
            password2 = binding.password2.text.toString(),
            address = binding.address.text.toString(),
            phone_number = binding.phoneNumber.text.toString(),
            isolated = true,
            maxDistanceAccepted = binding.maxDistance.text.toString().toInt(),
            startHour = binding.startHour.text.toString(),
            finalHour = binding.finalHour.text.toString()
        )
}