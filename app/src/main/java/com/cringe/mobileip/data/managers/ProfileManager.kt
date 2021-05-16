package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.model.profileinfo.InfoRequestAnswer
import io.ktor.http.*
import kotlinx.coroutines.runBlocking



class ProfileManager(
) {


    fun getInfo(): InfoRequestAnswer {


            val sm = ServerManager()
            var response: InfoRequestAnswer
            runBlocking {
                response = sm.sendRequest<InfoRequestAnswer>(
                    "",
                    "https://reqres.in/api/users/2",
                    HttpMethod.Get
                )
            }
            return response;

    }


}