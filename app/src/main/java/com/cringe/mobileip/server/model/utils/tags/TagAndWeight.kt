package com.cringe.mobileip.server.model.utils.tags

import kotlinx.serialization.Serializable

@Serializable
data class TagAndWeight(
    val name: String,
    val quantity: Int
)