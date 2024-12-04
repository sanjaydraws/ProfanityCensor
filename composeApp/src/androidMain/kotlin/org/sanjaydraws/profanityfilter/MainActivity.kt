package org.sanjaydraws.profanityfilter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App() // Directly passing the injected client
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}