package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.model.register.RegisterAnswer
import com.cringe.mobileip.server.model.register.RegisterUserData
import com.cringe.mobileip.server.model.utils.Endpoints
import com.cringe.mobileip.server.model.utils.Result
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import java.io.IOException

class RegisterManager {
    fun register(user: RegisterUserData): Result<RegisterAnswer> {
        try {
            val sm = ServerManager()
            var response: RegisterAnswer
            runBlocking {
                response = sm.sendRequest<RegisterAnswer>(
                    kotlinx.serialization.json.Json.encodeToString(user),
                    Endpoints().registerURL,
                    HttpMethod.Post
                )
            }
            return if(response.success == 1) {
                Result.Success(response)
            } else {
                Result.Failure(response)
            }
        } catch (e: Throwable) {
            return Result.Exception(IOException("Error registering", e))
        }
    }
}