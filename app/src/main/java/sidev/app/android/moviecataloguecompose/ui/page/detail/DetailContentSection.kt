package sidev.app.android.moviecataloguecompose.ui.page.detail

import android.annotation.SuppressLint
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import sidev.app.android.moviecataloguecompose.core.domain.model.Cast
import sidev.app.android.moviecataloguecompose.core.domain.model.ImgData
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.ui.theme.*
import sidev.app.android.moviecataloguecompose.ui.widget.DefaultLoading
import sidev.app.android.moviecataloguecompose.ui.widget.Img
import sidev.app.android.moviecataloguecompose.ui.widget.VerticalSpacer
import sidev.app.android.moviecataloguecompose.util.getDirectorNames
import sidev.app.android.moviecataloguecompose.util.getProducerNames
import sidev.app.android.moviecataloguecompose.util.stdFormat
import sidev.app.android.moviecataloguecompose.util.stdLandscapeMoviePosterRatio

@Composable
fun DetailContentSection(detail: MovieDetail?) {
  if(detail != null) {
    Column {
      VerticalSpacer(height = 15.dp)
      if(detail.tagline?.isNotBlank() == true) {
        Text(
          text = detail.tagline,
          style = getStdBoldItalicTextStyle(Size1).copy(
            color = TransOppositeDarkColor2
          ),
          modifier = Modifier.padding(
            horizontal = 10.dp,
          ),
        )
        VerticalSpacer(height = 20.dp)
      }
      Info(data = detail)
      if(detail.overview?.isNotBlank() == true) {
        VerticalSpacer(height = 20.dp)
        Overview(text = detail.overview)
      }
      VerticalSpacer(height = 20.dp)
      Cast(dataList = detail.casts)
      VerticalSpacer(height = 20.dp)
      Photos(imgList = detail.posters + detail.backdrops)
      VerticalSpacer(height = 20.dp)
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
    data.movie.date?.also { date ->
      Text(
        buildAnnotatedString {
          withStyle(titleStyle) {
            append("Release date:")
          }
          append(" ${date.stdFormat()}")
        }
      )
    }
    data.crews.getDirectorNames().also { list ->
      if(list.isNotEmpty()) {
        VerticalSpacer(height = 5.dp)
        Text(
          buildAnnotatedString {
            withStyle(titleStyle) {
              append("Directors:")
            }
            append(" ${list.joinToString()}")
          }
        )
      }
    }
    data.crews.getProducerNames().also { list ->
      if(list.isNotEmpty()) {
        VerticalSpacer(height = 5.dp)
        Text(
          buildAnnotatedString {
            withStyle(titleStyle) {
              append("Producers:")
            }
            append(" ${list.joinToString()}")
          }
        )
      }
    }
    data.homepage?.also { homepage ->
      if(homepage.isNotBlank()) {
        VerticalSpacer(height = 5.dp)
        Text(
          buildAnnotatedString {
            withStyle(titleStyle) {
              append("Homepage:")
            }
            append(" $homepage")
          }
        )
      }
    }
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
  /*
  val cardShape = RoundedCornerShape(10.dp)
  Card(
    modifier = modifier.shadow(
      elevation = 2.dp,
      shape = cardShape,
    ),
    shape = cardShape,
  ) {
  }
   */
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
      .width(100.dp)
      .padding(5.dp),
  ) {
    Img(
      img = data.profile,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .size(70.dp)
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
      color = TransOppositeDarkColor2,
    )
  }
}



@Composable
private fun Photos(imgList: List<ImgData>) {
  Column {
    Text(
      text = "Photos",
      style = getStdBoldTextStyle(Size1),
      modifier = Modifier.padding(start = 10.dp),
    )
    VerticalSpacer(height = 10.dp)
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      items(imgList.size) {
        PhotoItem(
          img = imgList[it],
          modifier = Modifier.height(100.dp).run {
            when(it) {
              0 -> padding(start = 10.dp)
              imgList.lastIndex -> padding(end = 10.dp)
              else -> this
            }
          }
        )
      }
    }
  }
}

@SuppressLint("ModifierParameter")
@Composable
private fun PhotoItem(
  img: ImgData,
  modifier: Modifier = Modifier.height(80.dp),
) {
  val cardShape = RoundedCornerShape(10.dp)
  Card(
    shape = cardShape,
    modifier = modifier
      .aspectRatio(
        stdLandscapeMoviePosterRatio
      )
      .shadow(
        elevation = 5.dp,
        shape = cardShape,
      ),
  ) {
    Img(
      img = img,
      contentScale = ContentScale.Crop,
      modifier = Modifier.fillMaxSize(),
    )
  }
}