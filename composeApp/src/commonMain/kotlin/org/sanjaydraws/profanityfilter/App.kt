package org.sanjaydraws.profanityfilter



import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.sanjaydraws.profanityfilter.censoredText.presentation.censor_filter.FilterWordsScreen



@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            FilterWordsScreen()
        }
    }
}






