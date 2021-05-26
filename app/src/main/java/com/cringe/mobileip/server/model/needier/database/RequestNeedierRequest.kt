package com.cringe.mobileip.server.model.needier.database

import kotlinx.serialization.Serializable

@Serializable
data class RequestNeedierRequest(
    val username: String,
    val tags: Map<String, Int>,
    val details: String
)