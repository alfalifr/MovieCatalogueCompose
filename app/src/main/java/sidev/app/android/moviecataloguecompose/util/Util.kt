package sidev.app.android.moviecataloguecompose.util

import android.content.Context
import android.util.Log
import android.util.TypedValue
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.javafaker.Faker
import sidev.app.android.moviecataloguecompose.ui.page.ViewModelFactory
import sidev.app.android.moviecataloguecompose.ui.theme.GreenLight
import sidev.app.android.moviecataloguecompose.ui.theme.Red
import java.text.SimpleDateFormat
import java.util.*

val faker = Faker()

@Composable
inline fun <reified T: ViewModel> defaulViewModel(): T =
  viewModel(factory = ViewModelFactory())


@Suppress("NAME_SHADOWING")
fun <T> List<T>.randomSubList(minSize: Int? = null): List<T> {
  val minSize = if(minSize in indices) minSize else 0
  val firstIndex = faker.random().nextInt(minSize ?: 0, size-(minSize ?: 0))
  val lastIndex = faker.random().nextInt(firstIndex +(minSize ?: 0), size)
  Log.e("TAG", "randomSubList: minSize= $minSize firstIndex= $firstIndex lastIndex= $lastIndex", )
  return subList(firstIndex, lastIndex)
}

fun <T> List<T>.getLoop(i: Int): T = this[i % size]


/**
 * Get a single color in certain [point] in color gradient between [first] and [last] color.
 *
 * [point] should have range between 0.0 - 1.0.
 * If [point] is less or greater then the range, then [point] will be
 * rounded up / down to the nearest value.
 */
fun getColorPointFromLinearGradient(
  first: Color,
  last: Color,
  point: Double, // from 0.0 - 1.0
): Color {
  if(point <= 0.0) return first
  if(point >= 1.0) return last

  //first.
  //val firstARGB = ARGBColor.fromColor(first);
  //final lastARGB = ARGBColor.fromColor(last);

  val aDiff = last.alpha - first.alpha
  val rDiff = last.red - first.red
  val gDiff = last.green - first.green
  val bDiff = last.blue - first.blue

  val aRes = (aDiff * point).toFloat() + first.alpha
  val rRes = (rDiff * point).toFloat() + first.red
  val gRes = (gDiff * point).toFloat() + first.green
  val bRes = (bDiff * point).toFloat() + first.blue

  return Color(
    alpha = aRes,
    red = rRes,
    green = gRes,
    blue = bRes,
  )
}

fun getScoreColor(
  score: Number,
  scale: Int = Const.SCORE_SCALE,
): Color = getColorPointFromLinearGradient(
  first = Red,
  last = GreenLight,
  point = score.toDouble() / scale,
)

fun formatScoreStr(score: Number): String = String.format("%.1f", score.toFloat()) +" %"
fun formatDurationStr(minutes: Number): String {
  val minInt = minutes.toInt()

  return if(minInt <= 60) "${minInt}m"
  else "${minInt /60}h ${minInt %60}m"
}

val BoxWithConstraintsScope.maxSize
  get() = Size(
    width = maxWidth.value,
    height = maxHeight.value,
  )


fun Date.stdFormat(
  isMonthLong: Boolean = true,
): String {
  val sdf = SimpleDateFormat("dd ${if(isMonthLong) "MMMM" else "MMM"} yyyy", Locale.ENGLISH)
  return sdf.format(this)
}

val stdApiDateFormat: SimpleDateFormat by lazy {
  SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
}


fun <T> Sequence<T>.collectFirst(count: Int? = null): List<T> {
  if(count == null) return toList()

  val res = mutableListOf<T>()

  val itr = iterator()
  if(!itr.hasNext())
    return res

  for(i in 0 until count) {
    if(!itr.hasNext()) break
    res += itr.next()
  }
  return res
}


val Int.pxToDp: Dp
  @Composable
  get() = (this / LocalContext.current.resources.displayMetrics.density).dp

fun Int.pxToDp(context: Context): Dp =
  (this / context.resources.displayMetrics.density).dp

fun Int.pxToDpSimple(context: Context): Float =
  this / context.resources.displayMetrics.density

/*
fun NavGraphBuilder.composable(
  route: String,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  themeBlock: @Composable (() -> PaddingValues)? = null,
  content: @Composable (NavEntry) -> Unit
) = androidComposable(
  route = route,
  arguments = arguments,
  deepLinks = deepLinks,
) {

  content(NavEntry(it, ))
}
 */