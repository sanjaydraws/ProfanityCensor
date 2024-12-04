package networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.sanjaydraws.profanityfilter.core.data.ApiConfig

fun createKtorHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    // Log the message to Logcat
                    println(message)
                }
            }
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        // Setting the base URL for all requests
        defaultRequest {
            url(ApiConfig.BASE_URL)
            headers.append("X-Api-Key", "1febc51b-1493-49f6-a85b-15f684269321")
            contentType(ContentType.Application.Json) // change multipart/form-data for file uploads: handle separate
        }
    }
}