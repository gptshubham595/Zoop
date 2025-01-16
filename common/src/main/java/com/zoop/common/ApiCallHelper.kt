package com.zoop.common

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.io.IOException

suspend inline fun <reified T, R> HttpClient.safeApiCall(
    url: String,
    method: HttpMethod,
    headers: Map<String, String> = emptyMap(),
    parameters: Map<String, String> = emptyMap(),
    noinline mapper: ((T) -> R)
): ResultWrapper<R> {
    return try {
        val response: T = request(url) {
            this.method = method
            url {
                this.parameters.appendAll(Parameters.build {
                    parameters.forEach { (key, value) ->
                        append(key, value)
                    }
                })
            }
            headers.forEach { (key, value) ->
                header(key, value)
            }
            contentType(ContentType.Application.Json)
        }.body<T>()
        ResultWrapper.Success(mapper(response))
    } catch (e: ClientRequestException) {
        ResultWrapper.Failure(e)
    } catch (e: ServerResponseException) {
        ResultWrapper.Failure(e)
    } catch (e: IOException) {
        ResultWrapper.Failure(e)
    } catch (e: Exception) {
        ResultWrapper.Failure(e)
    }

}