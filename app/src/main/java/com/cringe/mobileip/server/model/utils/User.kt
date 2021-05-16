package com.cringe.mobileip.server.model.utils

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String
)