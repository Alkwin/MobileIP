package com.cringe.mobileip.server.model.utils

data class Endpoints(
    var BASE_URL: String = "https://users-bd.herokuapp.com",
    var loginURL: String = "$BASE_URL/api/users/login",
    var registerURL: String = "$BASE_URL/api/users/register"
)
