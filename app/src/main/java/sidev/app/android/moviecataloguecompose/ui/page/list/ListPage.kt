package sidev.app.android.moviecataloguecompose.ui.page.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.ui.page.ViewModelFactory
import sidev.app.android.moviecataloguecompose.ui.theme.MovieCatalogueComposeTheme
import sidev.app.android.moviecataloguecompose.util.defaulViewModel

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
    LazyColumn(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      items(dataList) {
        Box(
          modifier = Modifier.padding(10.dp),
        ) {
          MovieItem(it, onClick = onItemClick,)
        }
      }
    }
  }
}

@Composable
fun MovieItem(
  data: Movie,
  onClick: ((Movie) -> Unit)? = null,
) {
  Card(
    modifier = Modifier.fillMaxWidth().run {
       if(onClick != null) clickable { onClick(data) } else this
    },
  ) {
    Column(
      modifier = Modifier.padding(15.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painter = rememberImagePainter(data = data.posterUrl),
        contentDescription = null,
        modifier = Modifier.size(
          300.dp,
          230.dp,
        )
      )
      Spacer(modifier = Modifier.height(10.dp))
      Text(data.title)
      Spacer(modifier = Modifier.height(10.dp))
      Text(data.release.toString())
    }
  }
}


@Preview(showBackground = true)
@Composable
private fun pagePreview() {
  ListPage()
}