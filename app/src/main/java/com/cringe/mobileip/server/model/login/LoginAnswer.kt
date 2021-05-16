package com.cringe.mobileip.server.model.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginAnswer(
    val token: String
)
