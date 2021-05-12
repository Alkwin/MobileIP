package com.cringe.mobileip.server

import kotlinx.serialization.Serializable

@Serializable
data class RegisterAnswer(
    val id: Int,
    val token:String
)
