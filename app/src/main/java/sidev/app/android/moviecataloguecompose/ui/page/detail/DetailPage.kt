package sidev.app.android.moviecataloguecompose.ui.page.detail

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.insets.*
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.ui.theme.*
import sidev.app.android.moviecataloguecompose.ui.widget.VerticalSpacer
import sidev.app.android.moviecataloguecompose.util.Const
import sidev.app.android.moviecataloguecompose.util.defaulViewModel
import sidev.app.android.moviecataloguecompose.util.maxSize

@Composable
fun DetailPage(
  movieId: Int,
  movieType: String,
  navController: NavController,
  systemPadding: PaddingValues? = null,
  viewModel: DetailViewModel = defaulViewModel()
) {

  viewModel.movieId.value = movieId
  viewModel.movieType.value = movieType

  val detail = viewModel.movieDetail.observeAsState().value
  //val navController = rememberNavController()

  BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
    DetailMainLayout(
      detail = detail,
      parentSize = maxSize.also {
        Log.e("TAG", "DetailPage: constraints= $constraints maxSize= $maxSize", )
      },
      systemPadding = systemPadding,
    )

    val topPadding = systemPadding?.calculateTopPadding()
      ?: 0.dp

    Box(
      modifier = Modifier
        .align(Alignment.TopStart)
        .padding(15.dp)
        .padding(top = topPadding)
        .size(50.dp),
    ) {
      Image(
        painter = ColorPainter(
          TransOppositeDarkColor4
        ),
        contentDescription = null,
        modifier = Modifier
          .fillMaxSize()
          .clip(CircleShape)
          .clickable {
            navController.popBackStack()
          },
      )
      Icon(
        imageVector = Icons.Rounded.ArrowBack,
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier
          .fillMaxSize()
          .padding(10.dp)
          .align(Alignment.Center),
      )
    }
  }
/*
    if(detail != null) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Img(img = detail.movie.poster,)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = detail.movie.title)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = detail.movie.date.toString())
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = detail.overview)
      }
    } else {
      CircularProgressIndicator()
    }
 */
}

@Composable
private fun DetailMainLayout(
  detail: MovieDetail?,
  parentSize: Size,
  systemPadding: PaddingValues?,
) {
  Column(
    modifier = Modifier
      .size(
        width = parentSize.width.dp,
        height = parentSize.height.dp,
      )
      .verticalScroll(
        rememberScrollState()
      ),
  ) {
    DetailMainHeader(
      detail = detail,
      parentSize = parentSize,
    )
    DetailContentSection(detail = detail)
    VerticalSpacer(height = systemPadding?.calculateBottomPadding() ?: 0.dp)
  }
}


@Preview(showBackground = true)
@Composable
private fun pagePreview() {
  DetailPage(
    movieId = 0,
    movieType = Const.KEY_TV,
    navController = NavController(
      LocalContext.current
    ),
    systemPadding = PaddingValues(0.dp)
  )
}