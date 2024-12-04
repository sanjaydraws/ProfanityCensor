package org.sanjaydraws.profanityfilter.censoredText.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import networking.CensorRequest
import networking.CensoredText
import org.sanjaydraws.profanityfilter.core.data.ApiConfig
import org.sanjaydraws.profanityfilter.core.data.safeApiCall
import org.sanjaydraws.profanityfilter.core.domain.ResultResponse



class CensoredRemoteDataSource(
    private val httpClient: HttpClient
): CensoredDataSource {

//    override suspend fun filterCensoredText(uncensored: String, replacement: String): ResultResponse<CensoredText> {
//        val requestPayload = CensorRequest(uncensored, replacement)
//
//
//        return try {
//            val response = httpClient.post(ApiConfig.PROFANITY_CENSORED) {
////                contentType(ContentType.Application.Json) // change multipart/form-data for file uploads:
//                setBody(Json.encodeToString(requestPayload))
////                headers.append("X-Api-Key", "1febc51b-1493-49f6-a85b-15f684269321")
//            }
//
//            if (response.status.isSuccess()) {
//                // Deserialize the response body
//                val censoredText = response.body<CensoredText>()
//                // Return ResultResponse.Success
//                ResultResponse.Success(censoredText)
//            } else {
//                // Handle HTTP error codes explicitly
//                ResultResponse.Failure(Exception("HTTP Error: ${response.status.value}"))
//            }
//        } catch (e: Exception) {
//            // Catch generic exceptions and return them as failure
//            ResultResponse.Failure(e)
//        }
//    }
override suspend fun filterCensoredText(uncensored: String, replacement: String): ResultResponse<CensoredText> {
    val requestPayload = CensorRequest(uncensored, replacement)

    return safeApiCall(
        apiCall = {
            httpClient.post(ApiConfig.PROFANITY_CENSORED) {
                setBody(Json.encodeToString(requestPayload))
            }
        },
        parseResponse = { response ->
            response.body() // Deserialize into CensoredText
        }
    )
}
}
