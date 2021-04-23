package com.cringe.mobileip.ui.data

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.ui.data.model.RegisterUserData
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import java.io.IOException

class RegisterManager {
    fun register(user: RegisterUserData): Result<RegisterUserData> {
        return try {
            val sm = ServerManager<RegisterUserData>()
            var response = ""
            runBlocking {
                response = sm.sendRequest(
                    kotlinx.serialization.json.Json.encodeToString(user),
                    "https://reqres.in/api/login",
                    HttpMethod.Get
                )
            }
            sm.interpretReturnMessage(response, user)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }
}