package com.cringe.mobileip.server

import com.cringe.mobileip.ui.data.Result
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

val TAG = "ServerManager"

class ServerManager {

    private var errorMessage = "-1"

    suspend inline fun <reified V: Any> sendRequest(
        reqBody: String,
        reqURL: String,
        reqMethod: HttpMethod
    ): V {
        //Makes the request
        val client = HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
            //Logging displays information about the request in the Console
        }
        //val response = client.request<V>(reqURL) did not work for some reason (to directly deserialize it)
        val response = client.request<String>(reqURL) {
            method = reqMethod
            body = reqBody
            contentType(ContentType.Application.Json.withParameter("charset", "utf-8"))
        }
        println("------$response------")
        //Deserializes the body of the response into the type V
        val answer = Json.decodeFromString<V>(response)
        println("======$answer======")
        return answer
    }

    fun <T: Any> interpretReturnMessage(user: T): Result<T> {
        return if(true) {
            Result.Success(user)
        } else {
            Result.Failure(user)
        }
    }
}