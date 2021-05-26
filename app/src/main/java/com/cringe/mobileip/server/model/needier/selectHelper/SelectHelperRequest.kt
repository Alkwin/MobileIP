package com.cringe.mobileip.server.model.needier.selectHelper

import kotlinx.serialization.Serializable

@Serializable
data class SelectHelperRequest(
    val usernameNeeder: String,
    val usernameHelper: String
)