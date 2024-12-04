package org.sanjaydraws.profanityfilter

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.sanjaydraws.profanityfilter.di.configureKoin

fun main() = application {
    configureKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "ProfanityFilter",
        ) {
            App(
//                client = remember {
//                    ApiServiceClient(createKtorHttpClient(OkHttp.create()))
//                }
            )
        }
    }
}