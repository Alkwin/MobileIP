package com.cringe.mobileip.server.model.profileinfo

import kotlinx.serialization.Serializable

@Serializable
data class Support(
    val url: String,
    val text: String
) {
    override fun toString(): String {
        return "Support(url='$url', text='$text')"
    }
}
