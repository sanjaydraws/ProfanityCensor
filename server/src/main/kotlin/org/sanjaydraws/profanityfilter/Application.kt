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
import java.io.FileNotFoundException
import java.io.InputStream

// Data class to handle the request input
data class ProfanityRequest(val text: String, val replacement:String? = "*")

// Response structure

const val SERVER_PORT = 8080
//val API_KEY: String = UUID.randomUUID().toString()

const val API_KEY: String = "1febc51b-1493-49f6-a85b-15f684269321"


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
        route("/profanitycensor") {
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
                val response = censorText(request.text, profaneWords, request.replacement)

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

fun censorText(text: String, profaneWords: List<String>, replacement: String?): ProfanityResponse {
    var censoredText = text
    var hasProfanity = false
    var totalProfaneWords = 0
    val profaneWordCounts = mutableMapOf<String, Int>()
    val effectiveReplacement = replacement ?: "*" // Default to "*" if null

    profaneWords.forEach { word ->
        val count = Regex("\\b${Regex.escape(word)}\\b", RegexOption.IGNORE_CASE).findAll(text).count()

        if(count>0){
            censoredText = censoredText.replace(word, effectiveReplacement.repeat(word.length), ignoreCase = true)
            hasProfanity = true
            totalProfaneWords += count
            profaneWordCounts[word] = count
        }
    }

    return ProfanityResponse(
        original = text,
        censored = censoredText,
        hasProfanity = hasProfanity,
        statistics = ProfanityStatistics(totalProfaneWords, profaneWordCounts)
    )
}
