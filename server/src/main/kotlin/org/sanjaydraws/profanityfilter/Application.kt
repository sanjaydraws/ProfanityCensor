package org.sanjaydraws.profanityfilter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.gson.gson
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.UUID

// Data class to handle the request input
data class ProfanityRequest(val text: String)

// Response structure

const val SERVER_PORT = 8080
val API_KEY: String = UUID.randomUUID().toString()

fun main() {
    println("API Key: $API_KEY") // Print the API key to the console for testing purposes

    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            serializeNulls()  // Ensure null values are included in the JSON
        }
    }

    routing {
        route("/profanityfilter") {
            post {
                val apiKey = call.request.headers["X-Api-Key"]
                if (apiKey == null || apiKey != API_KEY) {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid or missing API key")
                    return@post
                }

                // Deserialize the JSON into the ProfanityRequest data class
                val request = call.receive<ProfanityRequest>()

                // Load profane words and process the text field
                val profaneWords = loadProfaneWords()
                val response = censorText(request.text, profaneWords)

                // Respond with the censored text and status
                call.respond(response)
            }
        }
    }
}

fun loadProfaneWords(): List<String> {
    val classLoader = Thread.currentThread().contextClassLoader
    val inputStream: InputStream = classLoader.getResourceAsStream("words.json")
        ?: throw FileNotFoundException("Resource not found: words.json")
    val wordsJson = inputStream.bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<String>>() {}.type
    return Gson().fromJson(wordsJson, type)
}

fun censorText(text: String, profaneWords: List<String>): ProfanityResponse {
    var censoredText = text
    var hasProfanity = false

    profaneWords.forEach { word ->
        if (text.contains(word, ignoreCase = true)) {
            censoredText = censoredText.replace(word, "*".repeat(word.length), ignoreCase = true)
            hasProfanity = true
        }
    }

    return ProfanityResponse(
        original = text,    // The original input text
        censored = censoredText,  // Censored version
        has_profanity = hasProfanity  // Whether profanity was found
    )
}
