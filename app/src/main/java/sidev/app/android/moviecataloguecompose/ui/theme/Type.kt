package sidev.app.android.moviecataloguecompose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val LightTypography = Typography(
  body1 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = Color.Black,
  )
  /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val DarkTypography = Typography(
  body1 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = Color.White,
  )
)

/*
=================
TextStyle
=================
 */

val Bold = TextStyle(
  fontWeight = FontWeight.Bold
)
val Italic = TextStyle(
  fontStyle = FontStyle.Italic
)
val BoldItalic = TextStyle(
  fontWeight = FontWeight.Bold,
  fontStyle = FontStyle.Italic,
)


val SizeMin6 = 0.09
val SizeMin5 = 0.125
val SizeMin4 = 0.25
val SizeMin3 = 0.5
val SizeMin2 = 0.7
val SizeMin1 = 0.9
val Size0 = 1.0
val Size1 = 1.2
val Size2 = 1.5
val Size3 = 1.8
val Size4 = 2.0
val Size5 = 2.3
val Size6 = 2.5
val Size7 = 2.7
val Size8 = 3.0

@Composable
fun getStdTextStyle(
  factor: Double = Size0,
  isBold: Boolean = false,
  isItalic: Boolean = false,
): TextStyle {
  val size = MaterialTheme.typography.body1.fontSize * factor
  return TextStyle(
    fontSize = size,
    fontWeight = if(isBold) FontWeight.Bold else null,
    fontStyle = if(isItalic) FontStyle.Italic else null,
    color = MaterialTheme.typography.body1.color,
  )
}

@Composable
fun getStdBoldTextStyle(
  factor: Double = Size0,
): TextStyle = getStdTextStyle(
  factor = factor,
  isBold = true,
)

@Composable
fun getStdItalicTextStyle(
  factor: Double = Size0,
): TextStyle = getStdTextStyle(
  factor = factor,
  isItalic = true,
)

@Composable
fun getStdBoldItalicTextStyle(
  factor: Double = Size0,
): TextStyle = getStdTextStyle(
  factor = factor,
  isBold = true,
  isItalic = true,
)

