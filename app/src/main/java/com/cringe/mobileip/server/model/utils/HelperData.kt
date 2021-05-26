package com.cringe.mobileip.server.model.utils

import kotlinx.serialization.Serializable

@Serializable
data class HelperData(
    val username: String,
    val adress: String,
    val distance: Int,
    val score: Double,
    val commonResources: Map<String, Int>
)