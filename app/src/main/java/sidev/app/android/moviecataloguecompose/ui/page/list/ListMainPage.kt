@file:OptIn(
  ExperimentalPagerApi::class,
  ExperimentalMaterialApi::class,
)

package sidev.app.android.moviecataloguecompose.ui.page.list

import android.util.Log
import androidx.compose.animation.core.SnapSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.ui.theme.BlackTrans4
import sidev.app.android.moviecataloguecompose.ui.theme.FollowingDark
import sidev.app.android.moviecataloguecompose.ui.theme.Yellow
import sidev.app.android.moviecataloguecompose.ui.widget.DefaultLoading
import sidev.app.android.moviecataloguecompose.util.defaulViewModel
import sidev.app.android.moviecataloguecompose.util.getMovieTypeStrRes
import sidev.app.android.moviecataloguecompose.util.pxToDpSimple

@Composable
fun ListMainPage(
  systemPadding: PaddingValues? = null,
  viewModel: ListViewModel = defaulViewModel(),
  onItemClick: ((Movie) -> Unit)? = null,
) {
  //val listState = rememberLazyListState()
  val pagerState = rememberPagerState(pageCount = 2).apply {
    viewModel.pageIndex.value = currentPage
  }

  Box {
    Pager(
      viewModel = viewModel,
      systemPadding = systemPadding,
      pagerState = pagerState,
      onItemClick = onItemClick,
    )
    /*
    HorizontalPager(state = pagerState) {
      ListPage(
        pageIndex = it,
        topPadding = systemPadding?.calculateTopPadding(),
        bottomPadding = systemPadding?.calculateBottomPadding(),
        listState = listState,
        viewModel = viewModel,
        onItemClick = onItemClick,
      )
    }
     */
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Row(
      horizontalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier
        .onGloballyPositioned {
          viewModel.topPadding.value =
            it.size.height.pxToDpSimple(context)
        }
        .fillMaxWidth()
        .background(BlackTrans4)
        .padding(
          top = systemPadding
            ?.calculateTopPadding()
            ?.plus(10.dp) ?: 10.dp,
          bottom = 10.dp
        ),
    ) {
      for(i in 0 until 2) {
        TabBtn(
          text = LocalContext.current.getString(getMovieTypeStrRes(i)),
          isSelected = viewModel.pageIndex.value == i,
        ) {
          if(viewModel.pageIndex.value != i) {
            viewModel.pageIndex.value = i
            coroutineScope.launch {
              pagerState.animateScrollToPage(
                page = i,
                animationSpec = SnapSpec(500),
                initialVelocity = 10f,
              )
            }
          }
        }
      }
    }
  }
}


@Composable
private fun TabBtn(
  text: String,
  isSelected: Boolean,
  onClick: () -> Unit,
) {
  val cardShape = RoundedCornerShape(15.dp)
  Card(
    backgroundColor = if(isSelected) MaterialTheme.colors.primary
      else FollowingDark,
    shape = cardShape,
    onClick = onClick,
  ) {
    Text(
      text = text,
      color = if(isSelected) Yellow
        else MaterialTheme.colors.primary,
      modifier = Modifier.padding(
        vertical = 10.dp,
        horizontal = 15.dp,
      )
    )
  }
}


@Composable
private fun Pager(
  viewModel: ListViewModel = defaulViewModel(),
  systemPadding: PaddingValues? = null,
  pagerState: PagerState? = null,
  onItemClick: ((Movie) -> Unit)? = null,
) {
  val topBarPadding = viewModel.topPadding.observeAsState().value
  Log.e("TAG", "Pager: topBarPadding= $topBarPadding", )

  if(topBarPadding != null) {
    val listState = rememberLazyListState()
    val usedPagerState = pagerState ?: rememberPagerState(pageCount = 2).apply {
      viewModel.pageIndex.value = currentPage
    }

    HorizontalPager(state = usedPagerState) {
      ListPage(
        pageIndex = it,
        topPadding = topBarPadding.dp,
        bottomPadding = systemPadding?.calculateBottomPadding(),
        listState = listState,
        viewModel = viewModel,
        onItemClick = onItemClick,
      )
    }
  } else {
    DefaultLoading()
  }
}