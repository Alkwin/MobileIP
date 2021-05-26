package com.cringe.mobileip.server.model.utils

object Endpoints {
    var BASE_URL_AUTH = "https://users-bd.herokuapp.com/api"
    var BASE_URL_MATCH = "https://matching-alg.herokuapp.com/match"
    const val BASE_URL_DB = "https://all-db.herokuapp.com/api/v1"
    const val BASE_URL_MOCK = "https://c1668d59-6918-4d08-a5d9-a58f7c98d0be.mock.pstmn.io"

    var loginURL = "$BASE_URL_AUTH/users/login"
    var registerURL = "$BASE_URL_AUTH/users/register"
    val registerNeederRequest = "${BASE_URL_MOCK}/requestNeeder"

    fun getMatchRequest(username: String) = "$BASE_URL_MOCK/user"
//    fun getMatchRequest(username: String) = "$BASE_URL_MATCH?user=$username"

    //    const val choseHelper = "${BASE_URL_DB}/chosenHelper"
    const val choseHelper = "https://43f9bbad-d979-4b24-8997-b61d84ddb466.mock.pstmn.io/chosenHelper"
    val statisticsURL : String = "https://all-db.herokuapp.com/api/v1/statisticForUser"
}