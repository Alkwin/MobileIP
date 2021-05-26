package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.model.needier.request.RequestNeedierAnswer
import com.cringe.mobileip.server.model.needier.request.RequestNeedierRequest
import com.cringe.mobileip.server.model.utils.Endpoints
import com.cringe.mobileip.server.model.utils.Result
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException

class HelperManager {
    fun checkOrder(request: CheckOrderRequest): Result<CheckOrderAnswer> {
        val serverManager = ServerManager()
        var response: RequestNeedierAnswer
        try {
            runBlocking {
                response = serverManager.sendRequest<RequestNeedierAnswer>(
                    Json.encodeToString(request),
                    Endpoints.requestNeedier,
                    HttpMethod.Post
                )
            }
            return if(response.message == "1") {
                Result.Success(response)
            } else {
                Result.Failure(response)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Exception(IOException("Server communication error", e))
        }
    }
}