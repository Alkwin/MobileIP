package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.model.profileinfo.InfoRequest
import com.cringe.mobileip.server.model.profileinfo.InfoResponse
import com.cringe.mobileip.server.model.utils.Endpoints
import com.cringe.mobileip.server.model.utils.Result
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import java.io.IOException


class ProfileManager(
) {


    fun getInfo(): InfoResponse {

        val reqBody = InfoRequest(AuthenticationManager.userName);
        val sm = ServerManager()
        var response: InfoResponse
        try{
            runBlocking {
                response = sm.sendRequest<InfoResponse>(
                    kotlinx.serialization.json.Json.encodeToString(reqBody),
                    Endpoints.statisticsURL,
                    HttpMethod.Put
                )
            }
            response.success=true;
            return response;
        }catch (e: Throwable) {
            e.printStackTrace()
            val response= InfoResponse(success = false, countHelper = 0, countNeeder = 0)
            return response
        }

    }


}