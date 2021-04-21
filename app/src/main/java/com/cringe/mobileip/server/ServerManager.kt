package com.cringe.mobileip.server

import android.util.Log
import com.cringe.mobileip.ui.data.model.LoggedInUser
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import kotlinx.serialization.encodeToString
import java.lang.Exception

val TAG = "ServerManager"

class ServerManager {
    suspend fun sendRequest(user: LoggedInUser): String {
        Log.v(TAG, "-----------------------------------")
        val client = HttpClient(CIO)
        val response: HttpResponse
        return try {
            // This will return 200
            response = client.get("https://reqres.in/api/login")

            /*Log.v(TAG, kotlinx.serialization.json.Json.encodeToString(user))
            Log.v(TAG, "{\n" +
                    "    \"email\": \"eve.holt@reqres.in\",\n" +
                    "    \"password\": \"cityslicka\"\n" +
                    "}")

            response = client.post {
                url("https://reqres.in/api/login")
                body = "{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}"
            }
            Log.v(TAG, "${response.status}")
            Log.v(TAG, "$response")
            client.close()*/
            response.status.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            "-1"
        }
    }
}