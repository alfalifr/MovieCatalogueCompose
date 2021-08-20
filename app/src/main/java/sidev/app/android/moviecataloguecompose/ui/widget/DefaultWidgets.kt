package sidev.app.android.moviecataloguecompose.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import sidev.app.android.moviecataloguecompose.ui.theme.GreenLight

@Composable
fun DefaultLoading(
  text: String? = "Loading...",
  modifier: Modifier = Modifier,
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier,
  ) {
    CircularProgressIndicator()
    Spacer(modifier = Modifier.height(10.dp))
    if(text != null) {
      Text(text)
    }
  }
}

@Composable
fun DefaultEmptyImg(
  text: String? = null,
  modifier: Modifier = Modifier.fillMaxSize(),
) {
  Box(modifier = modifier) {
    Image(
      painter = defaultColorPainter(),
      contentDescription = null,
    )
    if(text?.isNotBlank() == true) {
      Text(
        text = text,
        modifier = Modifier.align(Alignment.Center),
      )
    }
  }
}

fun defaultColorPainter(): Painter = ColorPainter(
  GreenLight
)

@Composable
fun HorizontalSpacer(width: Dp) {
  Spacer(modifier = Modifier.width(width))
}

@Composable
fun VerticalSpacer(height: Dp) {
  Spacer(modifier = Modifier.height(height))
}