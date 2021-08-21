package sidev.app.android.moviecataloguecompose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sidev.app.android.moviecataloguecompose.ui.theme.GreenLight
import sidev.app.android.moviecataloguecompose.ui.theme.Red
import sidev.app.android.moviecataloguecompose.util.Const
import sidev.app.android.moviecataloguecompose.util.formatScoreStr
import sidev.app.android.moviecataloguecompose.util.getColorPointFromLinearGradient
import sidev.app.android.moviecataloguecompose.util.getScoreColor

@Composable
fun ScoreItem(
  score: Number,
  scale: Int = Const.SCORE_SCALE,
  modifier: Modifier = Modifier,
) {
  Surface(
    color = Color.Black,
    modifier = modifier.fillMaxSize()
      .clip(CircleShape)
  ) {
    Box(
      modifier = Modifier
        //.background(Color.Black)
        .fillMaxSize(),
    ) {
      CircularProgressIndicator(
        modifier = Modifier
          .align(Alignment.Center)
          .fillMaxSize()
          .padding(3.dp),
        progress = score.toFloat() / scale,
        color = getScoreColor(
          score = score,
        )
      )
      Text(
        text = formatScoreStr(score),
        color = Color.White,
        modifier = Modifier.align(Alignment.Center),
      )
    }
  }
}


@Preview(
  showBackground = false,
  backgroundColor = 0xFFCCCCCC
)
@Composable
private fun scoreItemPreview() {
  ScoreItem(
    score = 58.9,
    modifier = Modifier.size(80.dp)
  )
}