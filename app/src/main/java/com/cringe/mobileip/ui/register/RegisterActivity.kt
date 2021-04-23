package com.cringe.mobileip.ui.register

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.databinding.ActivityRegisterBinding
import com.cringe.mobileip.ui.login.LoginViewModel
import com.cringe.mobileip.ui.login.LoginViewModelFactory

class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.email
        val password = binding.password
        val name = binding.name

        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory())
            .get(RegisterViewModel::class.java)

        binding.register.setOnClickListener {
            registerViewModel.register(
                email.text.toString(),
                password.text.toString(),
                name.text.toString()
            )
        }
    }
}