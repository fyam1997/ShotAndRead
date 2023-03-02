import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.skiko.toImage
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit


var bitmap: ImageBitmap? by mutableStateOf(null)

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Column {
            Button(
                onClick = {
                    GlobalScope.launch(Dispatchers.IO) {
                        val image = Robot().createScreenCapture(Rectangle(Toolkit.getDefaultToolkit().screenSize))
                        bitmap = image.toImage().toComposeImageBitmap()
                    }
                },
                content = {
                    Text("Cap")
                }
            )
            val bitmap = bitmap
            if (bitmap != null) {
                Image(
                    bitmap = bitmap,
                    contentDescription = "Picture",
                    modifier = Modifier.size(3800.dp)
                )
            } else {
                Text("Not Ready")
            }
        }
    }
}
