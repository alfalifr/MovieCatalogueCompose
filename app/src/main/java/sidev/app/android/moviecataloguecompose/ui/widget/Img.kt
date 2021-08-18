package sidev.app.android.moviecataloguecompose.ui.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import sidev.app.android.moviecataloguecompose.core.domain.model.AssetImg
import sidev.app.android.moviecataloguecompose.core.domain.model.ImgData
import sidev.app.android.moviecataloguecompose.core.domain.model.RemoteImg

//@SuppressLint("ModifierParameter")
@Composable
fun Img(
  img: ImgData?,
  modifier: Modifier = Modifier,
  contentDesc: String? = null,
  alignment: Alignment = Alignment.Center,
  contentScale: ContentScale = ContentScale.Fit,
  alpha: Float = DefaultAlpha,
  colorFilter: ColorFilter? = null
) {
  val painter = when(img) {
    is RemoteImg -> rememberImagePainter(data = img.link)
    is AssetImg -> painterResource(id = img.link)
    null -> defaultColorPainter()
  }
  Image(
    painter = painter,
    contentDescription = contentDesc,
    modifier = modifier,
    alignment = alignment,
    contentScale = contentScale,
    alpha = alpha,
    colorFilter = colorFilter,
  )
}


class ColorPainter(
  val color: Color,
): Painter() {
  /**
   * Return the intrinsic size of the [Painter].
   * If the there is no intrinsic size (i.e. filling bounds with an arbitrary color) return
   * [Size.Unspecified].
   * If there is no intrinsic size in a single dimension, return [Size] with
   * [Float.POSITIVE_INFINITY] in the desired dimension.
   * If a [Painter] does not have an intrinsic size, it will always draw within the full
   * bounds of the destination
   */
  override val intrinsicSize: Size
    get() = Size.Unspecified

  /**
   * Implementation of drawing logic for instances of [Painter]. This is invoked
   * internally within [draw] after the positioning and configuring the [Painter]
   */
  override fun DrawScope.onDraw() {
    drawRect(color = color)
  }
}