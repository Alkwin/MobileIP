package com.cringe.mobileip.server.model.helper.finish

import kotlinx.serialization.Serializable

@Serializable
data class FinishOrderRequest(
    val username: String,
    val available: Int
)
