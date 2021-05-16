package com.cringe.mobileip.server.model.register

import com.cringe.mobileip.server.model.utils.User
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserData(
    val user: User,
    val name: String
)
