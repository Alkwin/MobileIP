package com.cringe.mobileip.server.model.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserData(
    val name: String,
    val surname: String,
    val userName: String,
    var email: String,
    var password1: String,
    var password2: String,
    var address: String,
    var phone_number: String,
    var isolated: Boolean,
    var maxDistanceAccepted: Int,
    var startHour: String,
    var finalHour: String
)