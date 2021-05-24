package com.cringe.mobileip.ui.register.utils

data class RegisterFormState(
    val nameError: Int? = null,
    val surnameError: Int? = null,
    val userNameError: Int? = null,
    val emailError: Int? = null,
    val password1Error: Int? = null,
    val password2Error: Int? = null,
    val addressError: Int? = null,
    val phone_numberError: Int? = null,
    val isolatedError: Boolean = false,
    val maxDistanceAcceptedError: Int? = null,
    val startHourError: Int? = null,
    val finalHourError: Int? = null,
    val isDataValid: Boolean = false
)
