package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.model.login.LoginAnswer
import com.cringe.mobileip.server.model.utils.Endpoints
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.server.model.utils.User
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginManager {

    fun login(user: User): Result<LoginAnswer> {
        val sm = ServerManager()
        var response: LoginAnswer
        try {
            // TODO: handle loggedInUser authentication
            runBlocking {
                response = sm.sendRequest<LoginAnswer>(
                    kotlinx.serialization.json.Json.encodeToString(user),
                    Endpoints.loginURL,
                    HttpMethod.Post
                )
            }
            return if(response.success == 1) {
                Result.Success(response)
            } else {
                Result.Failure(response)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Exception(IOException("Server communication error", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}