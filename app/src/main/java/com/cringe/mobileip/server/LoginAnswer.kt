package com.cringe.mobileip.server

import kotlinx.serialization.Serializable

@Serializable
data class LoginAnswer(
    val token: String
)
