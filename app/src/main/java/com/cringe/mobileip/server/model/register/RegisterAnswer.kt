package com.cringe.mobileip.server.model.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterAnswer(
    val success: Int,
    val message: String
)
