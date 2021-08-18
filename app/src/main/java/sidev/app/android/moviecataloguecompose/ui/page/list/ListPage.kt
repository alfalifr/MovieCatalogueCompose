@file:OptIn(ExperimentalFoundationApi::class)

package sidev.app.android.moviecataloguecompose.ui.page.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sidev.app.android.moviecataloguecompose.core.data.dummyMovieList
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.ui.theme.*
import sidev.app.android.moviecataloguecompose.ui.theme.Size1
import sidev.app.android.moviecataloguecompose.ui.widget.DefaultLoading
import sidev.app.android.moviecataloguecompose.ui.widget.Img
import sidev.app.android.moviecataloguecompose.ui.widget.ScoreItem
import sidev.app.android.moviecataloguecompose.util.defaulViewModel
import sidev.app.android.moviecataloguecompose.util.stdPortraitMoviePosterRatio

@Composable
fun ListPage(
  onItemClick: ((Movie) -> Unit)? = null
) {
  MovieCatalogueComposeTheme {
    MovieList(onItemClick = onItemClick,)
  }
}

@Composable
fun MovieList(
  vm: ListViewModel = defaulViewModel(),
  onItemClick: ((Movie) -> Unit)? = null
) {
  val dataList = vm.movieList.observeAsState().value
  if(dataList != null) {
    LazyVerticalGrid(
      cells = GridCells.Fixed(2),
      modifier = Modifier.fillMaxHeight(),
    ) {
      items(dataList) {
        Box(
          modifier = Modifier.padding(10.dp),
        ) {
          MovieItem(it, onClick = onItemClick,)
        }
      }
    }
  } else {
    DefaultLoading()
  }
}

@Composable
fun MovieItem(
  data: Movie,
  onClick: ((Movie) -> Unit)? = null,
) {
  BoxWithConstraints(
    modifier = Modifier.run {
      if(onClick != null) {
        clickable {
          onClick(data)
        }
      } else this
    },
  ) {
    val scoreSize = maxWidth * 4.5f / 10
    Column(
      modifier = Modifier.padding(bottom = 10.dp),
    ) {
      val cardShape = RoundedCornerShape(corner = CornerSize(15.dp))
      Card(
        modifier = Modifier
          .aspectRatio(
            stdPortraitMoviePosterRatio
          )
          .shadow(
            elevation = 5.dp,
            shape = cardShape,
          ),
        shape = cardShape,
      ) {
        Img(
          img = data.poster,
          contentScale = ContentScale.Crop,
        )
      }
      Text(
        text = data.title,
        modifier = Modifier.padding(
          top = scoreSize /3 + 10.dp,
          start = 10.dp,
        ),
        style = getStdBoldTextStyle(
          factor = Size1,
        )
      )
      Text(
        text = data.date.toString(),
        modifier = Modifier.padding(
          top = 10.dp,
          start = 10.dp,
        ),
      )
    }
    val scoreYOffset = maxWidth / stdPortraitMoviePosterRatio - scoreSize *2 / 3
    ScoreItem(
      score = data.voteAverage,
      modifier = Modifier
        .size(scoreSize)
        .absoluteOffset(
          x = 15.dp,
          y = scoreYOffset,
        ),
    )
    
  }
  /*
  Card(
    modifier = Modifier.fillMaxWidth().run {
       if(onClick != null) clickable { onClick(data) } else this
    },
    shape = MaterialTheme.shapes.medium
  ) {
    Column(
      modifier = Modifier.padding(15.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Img(
        img = data.poster,
        modifier = Modifier.size(
          300.dp,
          230.dp,
        )
      )
      Spacer(modifier = Modifier.height(10.dp))
      Text(data.title)
      Spacer(modifier = Modifier.height(10.dp))
      Text(data.date.toString())
    }
  }
   */
}

/*
@Preview(showBackground = true)
@Composable
private fun pagePreview() {
  ListPage()
}
 */

/*
@Preview(showBackground = true)
@Composable
private fun prev() {
  Text("Halo")
}
 */

///*
@Preview(showBackground = true)
@Composable
private fun itemPreview() {
  MovieItem(data = dummyMovieList.random())
  /*
  MovieItem(data = Movie(
    id = 1,
    title = "aaa",
    date = Date(),
    voteAverage = 12,
    voteCount = 3,
    poster = RemoteImg("aa"),
    type = "adaa",
  ))
   */
}
// */