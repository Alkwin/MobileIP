package com.cringe.mobileip.server

import com.cringe.mobileip.data.managers.AuthenticationManager
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.auth.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

val TAG = "ServerManager"

class ServerManager {

    private var errorMessage = "-1"

    suspend inline fun <reified T : Any> sendRequest(
        reqBody: String,
        reqURL: String,
        reqMethod: HttpMethod
    ): T {
        val client = HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
        //val response = client.request<V>(reqURL) did not work for some reason (to directly deserialize it)
        val response = client.request<String>(reqURL) {
            method = reqMethod
            body = reqBody
            if(AuthenticationManager.token!="") {
                header("Authorization", "Basic ${AuthenticationManager.token}")
            }
            contentType(ContentType.Application.Json.withParameter("charset", "utf-8"))
        }
        return Json.decodeFromString(response)
    }
}