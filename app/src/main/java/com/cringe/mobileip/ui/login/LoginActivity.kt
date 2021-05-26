package com.cringe.mobileip.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.MainActivity
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.databinding.ActivityLoginBinding
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.ui.login.utils.LoginViewModelFactory
import com.cringe.mobileip.ui.register.RegisterActivity
import com.cringe.mobileip.utils.afterTextChanged


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    private var currentUserType = "Helper"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.name
        val password = binding.password
        val login = binding.login
        val loading = binding.loading
        val spinner: Spinner? = binding.spinnerType
        val errorMessage = binding.errorMessage

        initializeSpinner(spinner)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            login.isEnabled = loginState.isDataValid

            if (loginState.emailError != null) {
                email.error = getString(loginState.emailError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.answerLiveData.observe(this@LoginActivity, Observer {
            loading.visibility = View.INVISIBLE
            when(it) {
                is Result.Success -> {
                    startHomeActivity()
                    setResult(Activity.RESULT_OK)
                    finish()
                }
                is Result.Failure -> {
                    errorMessage?.text = it.data.message
                }
                is Result.Exception -> {
                    errorMessage?.text = it.exception.message
                }
            }
        })

        email.afterTextChanged {
            loginViewModel.loginDataChanged(
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    email.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            email.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }
        }

        login.setOnClickListener {
            loading.visibility = View.VISIBLE

            /*loginViewModel.login(
                email.text.toString(),
                password.text.toString()
            )*/

            startHomeActivity()
        }

        binding.register?.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun initializeSpinner(spinner: Spinner?) {
        val types = listOf("Helper", "Needer")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            types
        )
        spinner?.adapter = adapter
        spinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentUserType = types[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }

        }
    }

    private fun startHomeActivity() {
        val newIntent = Intent(this, MainActivity::class.java)
        AuthenticationManager.isHelper = currentUserType == "Helper"
        startActivity(newIntent)
    }
}

