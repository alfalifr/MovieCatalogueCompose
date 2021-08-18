package sidev.app.android.moviecataloguecompose.ui.page.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import sidev.app.android.moviecataloguecompose.ui.theme.MovieCatalogueComposeTheme
import sidev.app.android.moviecataloguecompose.util.defaulViewModel

@Composable
fun DetailPage(movieId: Int) {
  MovieCatalogueComposeTheme {
    val vm: DetailViewModel = defaulViewModel()
    vm.movieId.value = movieId

    val detail = vm.movieDetail.observeAsState().value

    if(detail != null) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Image(
          painter = rememberImagePainter(data = detail.movie.posterUrl),
          contentDescription = null,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = detail.movie.title)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = detail.movie.release.toString())
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = detail.desc)
      }
    } else {
      CircularProgressIndicator()
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun pagePreview() {
  DetailPage(movieId = 0)
}