package sidev.app.android.moviecataloguecompose.ui.page.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import sidev.app.android.moviecataloguecompose.core.domain.model.Cast
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.ui.theme.Size1
import sidev.app.android.moviecataloguecompose.ui.theme.Size2
import sidev.app.android.moviecataloguecompose.ui.theme.getStdBoldItalicTextStyle
import sidev.app.android.moviecataloguecompose.ui.theme.getStdBoldTextStyle
import sidev.app.android.moviecataloguecompose.ui.widget.DefaultLoading
import sidev.app.android.moviecataloguecompose.ui.widget.Img
import sidev.app.android.moviecataloguecompose.ui.widget.VerticalSpacer
import sidev.app.android.moviecataloguecompose.util.getDirectorNames
import sidev.app.android.moviecataloguecompose.util.getProducerNames

@Composable
fun DetailContentSection(detail: MovieDetail?) {
  if(detail != null) {
    Column {
      if(detail.tagline != null) {
        VerticalSpacer(height = 15.dp)
        Text(
          text = detail.tagline,
          style = getStdBoldItalicTextStyle(Size2),
          modifier = Modifier.padding(
            horizontal = 10.dp,
          ),
        )
      }
      VerticalSpacer(height = 15.dp)
      Info(data = detail)
      VerticalSpacer(height = 15.dp)
      Overview(text = detail.overview)
      VerticalSpacer(height = 15.dp)
      Cast(dataList = detail.casts)
      VerticalSpacer(height = 15.dp)
    }
  } else {
    DefaultLoading(
      modifier = Modifier.fillMaxSize()
    )
  }
}

@Composable
private fun Info(
  data: MovieDetail,
) {
  Column(
    modifier = Modifier.padding(
      horizontal = 10.dp,
    ),
  ) {
    val titleStyle = getStdBoldTextStyle().toSpanStyle()
    Text(
      buildAnnotatedString {
        withStyle(titleStyle) {
          append("Release date:")
        }
        append(" ${data.movie.date}")
      }
    )
    Text(
      buildAnnotatedString {
        withStyle(titleStyle) {
          append("Directors:")
        }
        append(" ${data.crews.getDirectorNames().joinToString()}")
      }
    )
    Text(
      buildAnnotatedString {
        withStyle(titleStyle) {
          append("Producers:")
        }
        append(" ${data.crews.getProducerNames().joinToString()}")
      }
    )
    Text(
      buildAnnotatedString {
        withStyle(titleStyle) {
          append("Homepage:")
        }
        append(" ${data.homepage}")
      }
    )
  }
}

@Composable
private fun Overview(
  text: String,
) {
  Column(
    modifier = Modifier.padding(
      horizontal = 10.dp,
    ),
  ) {
    Text(
      text = "Overview",
      style = getStdBoldTextStyle(Size1),
    )
    VerticalSpacer(height = 10.dp)
    Text(text = text,)
  }
}

@Composable
private fun Cast(
  dataList: List<Cast>,
) {
  Column {
    Text(
      text = "Cast",
      style = getStdBoldTextStyle(Size1),
      modifier = Modifier.padding(
        horizontal = 10.dp,
      ),
    )
    VerticalSpacer(height = 10.dp)
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      items(dataList.size) {
        CastItem(
          data = dataList[it],
          modifier = Modifier.run {
            when(it) {
              0 -> padding(start = 10.dp)
              dataList.lastIndex -> padding(end = 10.dp)
              else -> this
            }
          }
        )
      }
    }
  }
}

@Composable
private fun CastItem(
  data: Cast,
  modifier: Modifier = Modifier,
) {
  val cardShape = RoundedCornerShape(10.dp)
  Card(
    modifier = modifier.shadow(
      elevation = 2.dp,
      shape = cardShape,
    ),
    shape = cardShape,
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.width(100.dp)
        .padding(5.dp),
    ) {
      Img(
        img = data.profile,
        modifier = Modifier.size(70.dp)
          .clip(CircleShape),
      )
      VerticalSpacer(height = 10.dp)
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = data.name,
        textAlign = TextAlign.Start,
      )
      VerticalSpacer(height = 10.dp)
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = data.character,
        textAlign = TextAlign.Start,
      )
    }
  }
}