package sidev.app.android.moviecataloguecompose.ui.page.detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.ui.theme.MovieCatalogueComposeTheme
import sidev.app.android.moviecataloguecompose.util.defaulViewModel
import sidev.app.android.moviecataloguecompose.util.maxSize

@Composable
fun DetailPage(movieId: Int) {
  MovieCatalogueComposeTheme {
    val vm: DetailViewModel = defaulViewModel()
    vm.movieId.value = movieId

    val detail = vm.movieDetail.observeAsState().value

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
      DetailMainLayout(
        detail = detail,
        parentSize = maxSize.also {
          Log.e("TAG", "DetailPage: constraints= $constraints maxSize= $maxSize", )
        },
      )
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
}

@Composable
private fun DetailMainLayout(
  detail: MovieDetail?,
  parentSize: Size,
) {
  Column(
    modifier = Modifier
      .size(
        width = parentSize.width.dp,
        height = parentSize.height.dp,
      ).verticalScroll(
        rememberScrollState()
      ),
  ) {
    DetailMainHeader(
      detail = detail,
      parentSize = parentSize,
    )
    DetailContentSection(detail = detail)
  }
}


@Preview(showBackground = true)
@Composable
private fun pagePreview() {
  DetailPage(movieId = 0)
}