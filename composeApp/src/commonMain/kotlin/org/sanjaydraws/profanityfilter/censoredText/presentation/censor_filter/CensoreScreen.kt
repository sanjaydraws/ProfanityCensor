package org.sanjaydraws.profanityfilter.censoredText.presentation.censor_filter

import org.koin.core.annotation.KoinExperimentalAPI



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import org.koin.compose.viewmodel.koinViewModel
import org.sanjaydraws.profanityfilter.core.domain.ResultResponse

@OptIn(KoinExperimentalAPI::class)
@Composable
fun FilterWordsScreen(
    censorViewModel: CensorTextViewModel = koinViewModel() // Using Koin for ViewModel Injection
) {
    var uncensoredText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val censorResult by censorViewModel.censoredText.collectAsState() // Observing StateFlow

    // Trigger the censoring API call when the text changes
    LaunchedEffect(uncensoredText) {
        if (uncensoredText.isEmpty()) {
            censorViewModel.clearResult()
        } else {
            isLoading = true
            censorViewModel.censorText(uncensoredText)
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        // Input for uncensored text
        TextField(
            value = uncensoredText,
            onValueChange = { uncensoredText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter uncensored text") }
        )

        // Display censoring results based on state
        when (val result = censorResult) {
            is ResultResponse.Success -> {
                val response = result.data
                response?.let {
                    Text("Original Text: ${it.original}")
                    Text("Censored Text: ${it.censored}")
                    Text("Has Profanity: ${if (it.hasProfanity) "Yes" else "No"}")
                    Text("Total Profane Words: ${it.statistics.totalProfaneWords}")
                    it.statistics.profaneWordsCount?.entries?.forEach { (word, count) ->
                        Text("$word: $count")
                    }
                }
            }
            is ResultResponse.Failure -> {
                Text(
                    text = result.exceptionOrNull()?.message ?: "Unknown error occurred",
                    color = Color.Red
                )
            }
            null -> {
                // Default state when no result is available
                Text("Enter text to censor.")
            }
        }
    }
}