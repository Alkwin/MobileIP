package com.cringe.mobileip.server.model.utils

object Endpoints {
    var BASE_URL_AUTH = "https://users-bd.herokuapp.com/api"
    var BASE_URL_MATCH = "https://matching-alg.herokuapp.com/match"
    const val BASE_URL_DB = "https://all-db.herokuapp.com/api/v1"
    const val BASE_URL_MOCK = "https://c1668d59-6918-4d08-a5d9-a58f7c98d0be.mock.pstmn.io"
    const val BASE_URL_MOCK_2 = "https://966bc5f8-dd6c-4910-8763-f5fea3112c62.mock.pstmn.io"

    //var loginURL = "$BASE_URL_AUTH/users/login"
    var loginURL = "$BASE_URL_MOCK_2/api/v1/login"
    var registerURL = "$BASE_URL_AUTH/users/register"
    val registerNeederRequest = "${BASE_URL_MOCK}/requestNeeder"

    fun getMatchRequest(username: String) = "$BASE_URL_MOCK/user"
    //fun getMatchRequest(username: String) = "$BASE_URL_MATCH?user=$username"

    //const val choseHelper = "${BASE_URL_DB}/chosenHelper"
    const val choseHelper = "https://43f9bbad-d979-4b24-8997-b61d84ddb466.mock.pstmn.io/chosenHelper"

    //val statisticsURL : String = "https://all-db.herokuapp.com/api/v1/statisticForUser"
    val statisticsURL : String = "$BASE_URL_MOCK_2/api/v1/statisticForUser"

    val requestNeedier: String = "$BASE_URL_MATCH/api/v1/requestNeeder"

    //val requestTags: String = "$BASE_URL_MATCH/api/v1/needs"
    val requestTags: String = "$BASE_URL_MOCK_2/api/v1/needs"

    //val neederToHelp: String = "$BASE_URL_MATCH/neederToHelp"
    const val neederToHelp = "https://303a52bb-bf56-4273-a2e1-ed89d4ab727e.mock.pstmn.io/neederToHelp"
}