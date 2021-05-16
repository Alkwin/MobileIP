package com.cringe.mobileip.server.model.login

import com.cringe.mobileip.ui.login.utils.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)