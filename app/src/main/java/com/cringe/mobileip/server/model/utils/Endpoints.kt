package com.cringe.mobileip.server.model.utils

object Endpoints {
    var BASE_URL_AUTH: String = "https://users-bd.herokuapp.com/api"
    var loginURL: String = "$BASE_URL_AUTH/users/login"
    var registerURL: String = "$BASE_URL_AUTH/users/register"
    val requestNeedier: String = "https://all-db.herokuapp.com/api/v1/requestNeeder"
    val statisticsURL : String = "https://all-db.herokuapp.com/api/v1/statisticForUser"
}