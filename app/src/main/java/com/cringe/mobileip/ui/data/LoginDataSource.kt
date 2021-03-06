package com.cringe.mobileip.ui.data

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.ui.data.model.LoggedInUser
import kotlinx.coroutines.runBlocking
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val user = LoggedInUser(
                username,
                password
            )

            val sm = ServerManager()
            var response = ""
            // Not necessarily the best idea
           runBlocking {
                response = sm.sendRequest(user)
            }
            return if(response.contains("200")) {
                Result.Success(user)
            } else {
                Result.Failure(user)
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}