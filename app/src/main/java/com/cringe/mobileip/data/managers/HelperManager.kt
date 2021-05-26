package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.model.helper.form.HelperFormAnswer
import com.cringe.mobileip.server.model.helper.form.HelperFormRequest
import com.cringe.mobileip.server.model.utils.Endpoints
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.server.model.utils.Result.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException

class HelperManager {
//    fun checkOrder(request: CheckOrderRequest): Result<CheckOrderAnswer> {
//        val serverManager = ServerManager()
//        var response: RequestNeedierAnswer
//        try {
//            runBlocking {
//                response = serverManager.sendRequest<RequestNeedierAnswer>(
//                    Json.encodeToString(request),
//                    Endpoints.registerNeederRequest,
//                    HttpMethod.Post
//                )
//            }
//            return if(response.message == "1") {
//                Result.Success(response)
//            } else {
//                Result.Failure(response)
//            }
//        } catch (e: Throwable) {
//            e.printStackTrace()
//            return Result.Exception(IOException("Server communication error", e))
//        }
//    }

    fun sendForm(request: HelperFormRequest): Result<HelperFormAnswer> {
        val serverManager = ServerManager()
        var response: HelperFormAnswer
        try {
            runBlocking {
                response = serverManager.sendRequest(
                    Json.encodeToString(request),
                    Endpoints.registerNeederRequest,
                    HttpMethod.Post
                )
            }
            return if(response.message == "1") {
                Success(response)
            } else {
                Failure(response)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            return Exception(IOException("Server communication error", e))
        }
    }
}