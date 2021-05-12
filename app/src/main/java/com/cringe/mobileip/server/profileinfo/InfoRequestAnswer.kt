package com.cringe.mobileip.server.profileinfo

import kotlinx.serialization.Serializable

@Serializable
data class InfoRequestAnswer(
    val data: Data,
    val support: Support


) {
    override fun toString(): String {
        return "DATA: $data\nSUPPORT: $support"
    }
}
