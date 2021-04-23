package com.cringe.mobileip.ui.data

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.ui.data.model.User
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginManager {

    fun login(user: User): Result<User> {
        try {
            // TODO: handle loggedInUser authentication

            val sm = ServerManager<User>()
            var response = ""
            // Not necessarily the best idea
           runBlocking {
                response = sm.sendRequest(
                    kotlinx.serialization.json.Json.encodeToString(user),
                    "https://reqres.in/api/login",
                    HttpMethod.Get
                )
            }
            return sm.interpretReturnMessage(response, user)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}