package com.cringe.mobileip.server.model.profileinfo

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String

) {
    override fun toString(): String {
        return "Data(id=$id, email='$email', first_name='$first_name', last_name='$last_name', avatar='$avatar')"
    }
}
