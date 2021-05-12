package com.cringe.mobileip.server

import com.cringe.mobileip.ui.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserData(
    val user: User,
    val name: String
)
