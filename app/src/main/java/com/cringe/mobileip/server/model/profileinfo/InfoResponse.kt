package com.cringe.mobileip.server.model.profileinfo

import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    val countHelper: Int,
    val countNeeder: Int,
    var success : Boolean = true


) {
    companion object{
        var serverInfoSaved= false;
        var savedCountNeeder = 0;
        var savedCountHelper = 0 ;
    }
    override fun toString(): String {
        return "InfoRequestAnswer(countHelper=$countHelper, countNeeder=$countNeeder)"
    }
}