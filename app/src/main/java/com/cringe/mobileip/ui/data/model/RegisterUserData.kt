package com.cringe.mobileip.ui.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserData(
    val email: String,
    val password: String,
    val name: String
)
