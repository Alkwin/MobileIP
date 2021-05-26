package com.cringe.mobileip.server.model.helper.order

import kotlinx.serialization.Serializable

@Serializable
data class CheckOrderRequest(
    val username: String
)