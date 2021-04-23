package com.cringe.mobileip.server

import android.util.Log
import com.cringe.mobileip.ui.data.Result
import com.cringe.mobileip.ui.data.model.RegisterUserData
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.lang.Exception

val TAG = "ServerManager"

class ServerManager<T : Any> {

    private var errorMessage = "-1"

    suspend fun sendRequest(
        body: String,
        URL: String,
        method: HttpMethod
    ): String {
        Log.v(TAG, "-----------------------------------")
        val client = HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
        }
        val response: HttpResponse
        return try {
            response = client.request(URL) {
                this.method = method
                this.body = body
            }
            response.status.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage
        }
    }

    fun interpretReturnMessage(response: String, user: T): Result<T> {
        return if(response.contains("200")) {
            Result.Success(user)
        } else {
            Result.Failure(user)
        }
    }
}