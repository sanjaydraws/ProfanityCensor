package org.sanjaydraws.profanityfilter.core.data


import io.ktor.client.statement.*
import io.ktor.http.*
import org.sanjaydraws.profanityfilter.core.domain.ResultResponse

suspend fun <T> safeApiCall(
    apiCall: suspend () -> HttpResponse,
    parseResponse: suspend (HttpResponse) -> T
): ResultResponse<T> {
    return try {
        val response = apiCall()

        if (response.status.isSuccess()) {
            val data = parseResponse(response)
            ResultResponse.Success(data)
        } else {
            ResultResponse.Failure(Exception("HTTP Error: ${response.status.value} - ${response.status.description}"))
        }
    } catch (e: Exception) {
        ResultResponse.Failure(e)
    }
}
