import androidx.compose.ui.window.ComposeUIViewController
import org.sanjaydraws.profanityfilter.App
import org.sanjaydraws.profanityfilter.di.configureKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        configureKoin()
    }
) {

    App(
//        client = remember {
//            ApiServiceClient(createKtorHttpClient(Darwin.create()))
//        }
    )
}