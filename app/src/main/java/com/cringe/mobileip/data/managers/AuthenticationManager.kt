package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.model.login.LoginAnswer
import com.cringe.mobileip.server.model.register.RegisterAnswer
import com.cringe.mobileip.server.model.register.RegisterUserData
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.server.model.utils.User

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class AuthenticationManager(val loginManager: LoginManager,
                            val registerManager: RegisterManager
) {

    companion object {
        var user: User? = null
        var token: String = ""
        var userName: String = ""
    }

    init {
        user = null
    }

    fun logout() {
        user = null
        token = ""
        userName = ""
        loginManager.logout()
    }

    fun login(user: User): Result<LoginAnswer> {
        val result = loginManager.login(user)

        if (result is Result.Success) {
            setLoggedInUser(user, result.data)
        }

        return result
    }

    fun register(futureUser: RegisterUserData): Result<RegisterAnswer> {
        return registerManager.register(futureUser)
    }

    private fun setLoggedInUser(newUser: User, result: LoginAnswer) {
        user = newUser
        token = result.token
        userName = result.username
    }
}