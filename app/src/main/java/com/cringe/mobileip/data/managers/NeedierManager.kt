package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.model.needier.database.RequestNeedierAnswer
import com.cringe.mobileip.server.model.needier.database.RequestNeedierRequest
import com.cringe.mobileip.server.model.needier.matching.MatchingNeederHelperAnswer
import com.cringe.mobileip.server.model.needier.selectHelper.SelectHelperAnswer
import com.cringe.mobileip.server.model.needier.selectHelper.SelectHelperRequest
import com.cringe.mobileip.server.model.utils.Endpoints
import com.cringe.mobileip.server.model.utils.Result
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException

class NeedierManager {
    fun registerRequest(request: RequestNeedierRequest): Result<RequestNeedierAnswer> {
        val serverManager = ServerManager()
        var response: RequestNeedierAnswer
        try {
            runBlocking {
                response = serverManager.sendRequest(
                    Json.encodeToString(request),
                    Endpoints.registerNeederRequest,
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

    fun requestHelpers(neederUsername: String): Result<MatchingNeederHelperAnswer> {
        val serverManager = ServerManager()
        var response: MatchingNeederHelperAnswer
        try {
            runBlocking {
                response = serverManager.sendRequest(
                    "",
                    Endpoints.getMatchRequest(neederUsername),
                    HttpMethod.Post
                )
            }
            return if(response.helperResponses.isNotEmpty()) {
                Result.Success(response)
            } else {
                Result.Failure(response)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Exception(IOException("Server communication error", e))
        }
    }

    fun selectHelper(request: SelectHelperRequest): Result<SelectHelperAnswer> {
        val serverManager = ServerManager()
        var response: SelectHelperAnswer
        try {
            runBlocking {
                response = serverManager.sendRequest(
                    Json.encodeToString(request),
                    Endpoints.choseHelper,
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