package com.cringe.mobileip.server.model.utils

object Endpoints {
    var BASE_URL: String = "https://users-bd.herokuapp.com"
    var loginURL: String = "$BASE_URL/api/v1/users/login"
    var registerURL: String = "$BASE_URL/api/users/register"
    val requestNeedier: String = "https://all-db.herokuapp.com/api/v1/requestNeeder"
}
