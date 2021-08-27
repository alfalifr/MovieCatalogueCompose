@file:OptIn(
  ExperimentalPagerApi::class,
  ExperimentalMaterialApi::class,
)

package sidev.app.android.moviecataloguecompose.ui.page.list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.ui.theme.*
import sidev.app.android.moviecataloguecompose.ui.widget.DefaultLoading
import sidev.app.android.moviecataloguecompose.ui.widget.Img
import sidev.app.android.moviecataloguecompose.util.*


@Composable
fun TrendingCarousel(
  viewModel: ListViewModel = defaulViewModel(),
  listState: LazyListState? = null,
  onItemClick: ((Movie) -> Unit)? = null,
) {
  BoxWithConstraints(
    modifier = Modifier.fillMaxWidth(),
  ) {
    //val maxWidth = maxWidth
    val boxHeight = (maxWidth / stdLandscapeMoviePosterRatio).run {
      if(listState == null) {
        return@run this
      }
      /*
      val vpStartOffset = listState.layoutInfo.viewportStartOffset
      val firstVisItemOffset = listState.layoutInfo.visibleItemsInfo.firstOrNull()?.offset
      Log.e("TAG", "TrendingCarousel: vpStartOffset= $vpStartOffset firstVisItemOffset= $firstVisItemOffset", )
       */

      val diffDp = listState.firstVisibleItemScrollOffset.pxToDp(
        LocalContext.current
      )
      //listState.firstVisibleItemScrollOffset
      minus(diffDp)
    }

    if(boxHeight > 0.dp) {
      viewModel.loadTrendingList()
      val dataList = viewModel.trendingMovieList.observeAsState().value

      if(dataList != null) {
        viewModel.startActiveTrendingCycle()
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(boxHeight),
        ) {
          TrendingPager(
            dataList = dataList,
            //maxWidth = maxWidth,
            viewModel = viewModel,
            onItemClick = onItemClick,
          )
          CarouselIndicator(
            viewModel = viewModel,
            modifier = Modifier
              .height(35.dp)
              .align(Alignment.BottomCenter)
              .padding(10.dp),
          )
        }
      } else {
        DefaultLoading(
          modifier = Modifier.align(Alignment.Center)
        )
      }
    }
  }
}

@Composable
private fun TrendingPager(
  dataList: List<Movie>,
  //maxWidth: Dp,
  viewModel: ListViewModel = defaulViewModel(),
  onItemClick: ((Movie) -> Unit)? = null,
) {
  val activeIndex = viewModel.activeTrendingIndex.observeAsState().value
  if(activeIndex != null) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
      pageCount = dataList.size,
      infiniteLoop = true,
    )
    HorizontalPager(
      modifier = Modifier
        .fillMaxSize()
        //.height(maxWidth / stdLandscapeMoviePosterRatio)
        .onGloballyPositioned {
          coroutineScope.launch {
            pagerState.animateScrollToPage(
              activeIndex
            )
          }
        },
      state = pagerState,
      itemSpacing = 15.dp,
    ) {
      TrendingItem(
        data = dataList[it],
        onItemClick = onItemClick,
      )
    }
  }
}


@Composable
private fun TrendingItem(
  data: Movie,
  onItemClick: ((Movie) -> Unit)? = null,
) {
  val ctx = LocalContext.current
  BoxWithConstraints(
    modifier = Modifier.fillMaxSize()
      .run {
         if(onItemClick != null) clickable {
           onItemClick(data)
         } else this
      },
  ) {
    Img(
      img = data.poster,
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
    )
    Spacer(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            0f to TransFollowingDarkColor2,
            .6f to Color.Transparent,
            startY = maxHeight.px(ctx),
            endY = 0f,
          )
        )
    )
    Text(
      text = data.title,
      style = getStdBoldTextStyle(
        factor = Size2,
      ).copy(
        color = OppositeDark,
      ),
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(10.dp)
        .padding(bottom = 20.dp),
    )
  }
}


@Composable
private fun CarouselIndicator(
  viewModel: ListViewModel = defaulViewModel(),
  modifier: Modifier = Modifier,
) {
  val total = viewModel.trendingMovieList.observeAsState().value?.size
  val activeIndex = viewModel.activeTrendingIndex.observeAsState().value

  if(activeIndex != null && total != null) {
    BoxWithConstraints(
      modifier = modifier,
    ) {
      val coroutineScope = rememberCoroutineScope()
      val listState = rememberLazyListState()
      //val ctx = LocalContext.current
      val itemWidth = 20.dp
      val itemSpace = 10.dp

      LazyRow(
        modifier = Modifier
          .align(Alignment.Center)
          .fillMaxHeight(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(itemSpace)
      ) {
        items(total) {
          Log.e("TAG", "CarouselIndicator: activeIndex= $activeIndex it= $it it == activeIndex => ${it == activeIndex}", )
          Card(
            backgroundColor = if(it == activeIndex) MaterialTheme.colors.primary
              else Color.White,
            shape = CircleShape,
            modifier = Modifier
              .fillMaxHeight()
              .width(itemWidth)
              .shadow(
                shape = CircleShape,
                elevation = 5.dp,
              ),
          ) {
            //No content
          }
        }
        coroutineScope.launch {
          val offset = (itemWidth + itemSpace) * activeIndex -
            (maxWidth / 2) +
            (itemWidth / 2)

          Log.e("TAG", "CarouselIndicator: total= $total activeIndex= $activeIndex offset= $offset maxWidth= $maxWidth itemWidth= $itemWidth", )

          listState.animateScrollBy(
            value = offset.value
          )
        }
      }
    }
  }
}