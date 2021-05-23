package com.cringe.mobileip.ui.register

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.databinding.ActivityRegisterBinding
import com.cringe.mobileip.server.model.register.RegisterUserData
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.ui.register.utils.RegisterViewModelFactory

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

        val errorMessage = binding.errorMessage

        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory())
            .get(RegisterViewModel::class.java)

        binding.register.setOnClickListener {
            registerViewModel.register(
                RegisterUserData(
                    name = name.text.toString(),
                    surname = surname.text.toString(),
                    userName = userName.text.toString(),
                    email = email.text.toString(),
                    password1 = password1.text.toString(),
                    password2 = password2.text.toString(),
                    address = address.text.toString(),
                    phone_number = phoneNumber.text.toString(),
                    isolated = isolated,
                    maxDistanceAccepted = maxDistanceAccepted.text.toString().toInt(),
                    startHour = startHour.text.toString(),
                    finalHour = finalHour.text.toString()
                )
            )
        }

        registerViewModel.registerResult.observe(this@RegisterActivity, Observer {
            val registerResult = it ?: return@Observer

            if (registerResult is Result.Success) {
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                if(registerResult is Result.Failure) { // By checking we ensure the type of the Result and thus are able to access its parameters ('data')
                    errorMessage.setText(registerResult.data.message)
                } else if(registerResult is Result.Exception){
                    errorMessage.setText(registerResult.exception.message)
                }
            }
        })
    }
}