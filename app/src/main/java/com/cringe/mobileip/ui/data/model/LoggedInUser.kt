package com.cringe.mobileip.ui.data.model

import kotlinx.serialization.Serializable

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */

@Serializable
data class LoggedInUser(
    val email: String,
    val password: String
)