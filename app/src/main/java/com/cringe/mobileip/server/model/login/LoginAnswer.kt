package com.cringe.mobileip.server.model.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginAnswer(
    val success: Int,
    val data: String,
    val token: String
)
