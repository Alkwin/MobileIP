package com.cringe.mobileip.server.model.helper.form

import kotlinx.serialization.Serializable

@Serializable
data class HelperFormRequest(
    val username: String,
    val distanceAccepted: String,
    val tags: Map<String, Int>,
    val details: String
)

