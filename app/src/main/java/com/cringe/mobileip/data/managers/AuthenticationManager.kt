package com.cringe.mobileip.data.managers

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

    fun login(user: User): Result<User> {
        val result = loginManager.login(user)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    fun register(email: String,
                 password: String,
                 name:String
    ) : Result<RegisterUserData> {
        val result = registerManager.register(
            RegisterUserData(
            User(email, password), name)
        )

        //The user will register and then login manually

        return result
    }

    private fun setLoggedInUser(user: User) {
        this.user = user
    }
}