package com.lyadsky.peeker.data.network

import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.errors.AppError
import com.lyadsky.peeker.errors.Code
import com.lyadsky.peeker.exceptions.ServerException
import com.lyadsky.peeker.exceptions.SocketException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import io.ktor.util.StringValues
import io.ktor.utils.io.errors.IOException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository : KoinComponent {

    private val httpClient: HttpClient by inject()

    private val defaultHeaders = StringValues.build {
        "Content-Type" to "application/json"
    }

    protected suspend fun executeCall(
        type: HttpMethod,
        path: String,
        parameters: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        body: String? = null
    ): String {
        try {
            return execute(type, path, parameters, headers, body)
        } catch (e: SocketException) {
            throw AppError(code = Code.SERVER_ERROR, description = e.message)
        } catch (e: ServerException){
            throw AppError(code = Code.SERVER_ERROR, description = e.message)
        }
    }

    private suspend fun execute(
        type: HttpMethod,
        path: String,
        parameters: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        body: String? = null
    ): String {
        val response: HttpResponse
        try {
            response = httpClient.request(BuildKonfig.BASE_URL) {
                url {
                    appendPathSegments(path)
                    parameters?.forEach { this.parameters.append(it.key, it.value) }
                }
                method = type
                headers?.forEach { this.headers.append(it.key, it.value) }
                this.headers.appendAll(defaultHeaders)
                body?.let { setBody(it) }
            }
        } catch (e: SocketTimeoutException) {
            throw SocketException()
        } catch (e: IOException){
            throw SocketException()
        }
        if (response.status.value !in 200..299){
            throw ServerException(response.status.value, response.status.description)
        }
        return response.body()
    }
}