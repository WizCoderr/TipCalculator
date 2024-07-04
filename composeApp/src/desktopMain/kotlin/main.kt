import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.isTraySupported
import org.jetbrains.compose.resources.painterResource
import tipclc.composeapp.generated.resources.Res
import tipclc.composeapp.generated.resources.icon

fun main() = application {
    if(isTraySupported){
        Tray(
            icon = painterResource(Res.drawable.icon),
            menu = { Item("Exit", onClick = ::exitApplication)}
        )
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "TipCalculator",
    ) {
        App()
    }
}