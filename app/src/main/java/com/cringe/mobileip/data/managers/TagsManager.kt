package com.cringe.mobileip.data.managers

import com.cringe.mobileip.server.ServerManager
import com.cringe.mobileip.server.model.utils.Endpoints
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.server.model.utils.tags.Tag
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import java.io.IOException

class TagsManager {
    fun requestTags(): Result<Map<String, String>> {
        val serverManager = ServerManager()
        var response: Map<String, String>
        return try {
            runBlocking {
                response = serverManager.sendRequest<Map<String, String>>(
                    "",
                    Endpoints.requestTags,
                    HttpMethod.Get
                )
            }
            Result.Success(response)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Exception(IOException("Server communication error", e))
        }
    }
}