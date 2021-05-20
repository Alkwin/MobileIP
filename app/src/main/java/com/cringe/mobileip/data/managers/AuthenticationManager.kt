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

    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
        loginManager.logout()
    }

    fun login(user: User): Result<LoginAnswer> {
        val result = loginManager.login(user)

        if (result is Result.Success) {
            setLoggedInUser(user)
        }

        return result
    }

    fun register(futureUser: RegisterUserData): Result<RegisterAnswer> {
        return registerManager.register(futureUser)
    }

    private fun setLoggedInUser(user: User) {
        this.user = user
    }
}