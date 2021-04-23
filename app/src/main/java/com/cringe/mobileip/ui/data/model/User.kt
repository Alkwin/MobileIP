package com.cringe.mobileip.ui.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String
)