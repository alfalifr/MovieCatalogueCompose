package sidev.app.android.moviecataloguecompose.ui.page.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.sharp.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import sidev.app.android.moviecataloguecompose.core.domain.model.ImgData
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.ui.theme.*
import sidev.app.android.moviecataloguecompose.ui.widget.DefaultLoading
import sidev.app.android.moviecataloguecompose.ui.widget.HorizontalSpacer
import sidev.app.android.moviecataloguecompose.ui.widget.Img
import sidev.app.android.moviecataloguecompose.ui.widget.VerticalSpacer
import sidev.app.android.moviecataloguecompose.util.*


@Composable
fun DetailMainHeader(
  detail: MovieDetail?,
  parentSize: Size,
) {
  val boxHeight = (parentSize.width / stdLandscapeMoviePosterRatio) *14/10
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(boxHeight.dp),
  ) {
    MainBg(img = detail?.backdrops?.firstOrNull())
    Row(
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(vertical = 10.dp,)
        .padding(start = 10.dp),
      verticalAlignment = Alignment.Bottom,
    ) {
      val mainPosterWidth = parentSize.width /3.5
      MainPoster(
        img = detail?.movie?.poster,
        modifier = Modifier.width(mainPosterWidth.dp),
      )
      HorizontalSpacer(width = 15.dp)
      Column {
        detail?.movie?.let { movie ->
          Text(
            text = movie.title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = getStdBoldTextStyle(factor = Size2),
            modifier = Modifier.padding(end = 10.dp),
          )
          VerticalSpacer(height = 15.dp)
          MainHeaderRatingRow(
            detail = detail,
            modifier = Modifier.padding(end = 10.dp),
          )
          VerticalSpacer(height = 10.dp)
          GenreList(genres = detail.genres)
        }
      }
    }
  }
}


@Composable
private fun MainBg(img: ImgData?, ) {
  BoxWithConstraints(
    modifier = Modifier
      .aspectRatio(
        stdLandscapeMoviePosterRatio
      )
      .clip(
        GenericShape { size, layoutDirection ->
          drawMainBgShape(size)
        }
      ),
  ) {
    Img(
      img = img,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxSize(),
    )
    val imgHeight = maxWidth / stdPortraitMoviePosterRatio
    Spacer(
      modifier = Modifier.fillMaxSize()
        .background(
          Brush.verticalGradient(
            .0f to TransFollowingDarkColor2,
            //.4f to WhiteTrans2,
            .6f to Color.Transparent,
            startY = imgHeight.value,
            endY = imgHeight.value * .0f,
          )
        ),
    )
    if(img == null) {
      DefaultLoading(
        text = null,
        modifier = Modifier.align(Alignment.Center),
      )
    }
  }
}

private fun Path.drawMainBgShape(
  size: Size,
) {
  lineTo(0f, size.height *8/10)


  val xControl = size.width /2
  val yControl = size.height *12/10

  quadraticBezierTo(
    xControl,
    yControl,
    size.width,
    size.height *8/10,
  )

  lineTo(size.width, 0f)
  close()
}


@Composable
private fun MainPoster(
  img: ImgData?,
  modifier: Modifier = Modifier,
) {
  val cardShape = RoundedCornerShape(size = 15.dp)
  Card(
    shape = cardShape,
    modifier = modifier
      .aspectRatio(
        stdPortraitMoviePosterRatio
      )
      .shadow(
        elevation = 10.dp,
        shape = cardShape,
      ),
  ) {
    Box {
      Img(
        img = img,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
      )
      if(img == null) {
        DefaultLoading(
          text = null,
          modifier = Modifier.align(Alignment.Center),
        )
      }
    }
  }
}

@Composable
private fun MainHeaderRatingRow(
  detail: MovieDetail,
  modifier: Modifier = Modifier,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier,
  ) {
    Icon(
      imageVector = Icons.Filled.Star,
      contentDescription = null,
      tint = getScoreColor(detail.movie.voteAverage),
    )
    HorizontalSpacer(width = 10.dp)
    Text(
      text = formatScoreStr(detail.movie.voteAverage),
      style = getStdTextStyle(factor = Size1),
    )
    detail.duration?.let { duration ->
      HorizontalSpacer(width = 15.dp)
      Icon(
        imageVector = Icons.Outlined.Warning,
        contentDescription = null,
        tint = GreenLighter,
      )
      HorizontalSpacer(width = 10.dp)
      Text(
        text = formatDurationStr(duration),
        style = getStdTextStyle(factor = Size1),
      )
    }
  }
}

@Composable
private fun GenreList(
  genres: List<String>,
  modifier: Modifier = Modifier,
) {
  LazyRow(
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    modifier = modifier,
  ) {
    items(genres) {
      Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Grey4,
        modifier = Modifier.wrapContentSize()
      ) {
        Text(
          text = it,
          textAlign = TextAlign.Center,
          color = Color.White,
          modifier = Modifier.padding(
            vertical = 5.dp,
            horizontal = 10.dp,
          ),
        )
      }
    }
  }
}