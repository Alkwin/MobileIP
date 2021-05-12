package com.cringe.mobileip.ui.data

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.RegisterAnswer
import com.cringe.mobileip.server.RegisterUserData
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import java.io.IOException

class RegisterManager {
    fun register(user: RegisterUserData): Result<RegisterUserData> {
        return try {
            val sm = ServerManager()
            var response: RegisterAnswer
            runBlocking {
                response = sm.sendRequest<RegisterAnswer>(
                    kotlinx.serialization.json.Json.encodeToString(user.user),
                    "https://reqres.in/api/register",
                    HttpMethod.Post
                )
            }
            sm.interpretReturnMessage(user)
        } catch (e: Throwable) {
            Result.Error(IOException("Error registering", e))
        }
    }
}