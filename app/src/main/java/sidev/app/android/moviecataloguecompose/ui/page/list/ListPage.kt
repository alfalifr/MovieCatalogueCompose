@file:OptIn(ExperimentalFoundationApi::class)

package sidev.app.android.moviecataloguecompose.ui.page.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import sidev.app.android.moviecataloguecompose.core.data.dummyMovieList
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.ui.theme.*
import sidev.app.android.moviecataloguecompose.ui.theme.Size1
import sidev.app.android.moviecataloguecompose.ui.widget.DefaultLoading
import sidev.app.android.moviecataloguecompose.ui.widget.Img
import sidev.app.android.moviecataloguecompose.ui.widget.ScoreItem
import sidev.app.android.moviecataloguecompose.util.defaulViewModel
import sidev.app.android.moviecataloguecompose.util.stdFormat
import sidev.app.android.moviecataloguecompose.util.stdPortraitMoviePosterRatio

@Composable
fun ListPage(
  pageIndex: Int,
  topPadding: Dp? = null,
  bottomPadding: Dp? = null,
  listState: LazyListState = rememberLazyListState(),
  viewModel: ListViewModel = defaulViewModel(),
  onItemClick: ((Movie) -> Unit)? = null,
) {
  //val topPadding = systemPadding?.calculateTopPadding()
  //val bottomPadding = systemPadding?.calculateBottomPadding()

  MovieList(
    pageIndex = pageIndex,
    viewModel = viewModel,
    listState = listState,
    topPadding = topPadding,
    bottomPadding = bottomPadding,
    onItemClick = onItemClick,
  )
}

@Composable
fun MovieList(
  pageIndex: Int,
  viewModel: ListViewModel = defaulViewModel(),
  listState: LazyListState = rememberLazyListState(),
  topPadding: Dp? = null,
  bottomPadding: Dp? = null,
  onItemClick: ((Movie) -> Unit)? = null,
) {
  val dataList = viewModel.popularMovieList.observeAsState().value
  val vmPageIndex = viewModel.pageIndex.observeAsState().value

  if(dataList != null && vmPageIndex == pageIndex) {
    LazyVerticalGrid(
      state = listState,
      contentPadding = PaddingValues(
        top = topPadding ?: 0.dp,
        bottom = bottomPadding ?: 0.dp,
      ),
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
    DefaultLoading(
      modifier = Modifier.fillMaxSize(),
    )
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
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(
          top = scoreSize /3 + 10.dp,
          start = 10.dp,
        ),
        style = getStdBoldTextStyle(
          factor = Size1,
        )
      )
      data.date?.also { date ->
        Text(
          text = date.stdFormat(false),
          modifier = Modifier.padding(
            top = 10.dp,
            start = 10.dp,
          ),
        )
      }
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