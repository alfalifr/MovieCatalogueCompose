package sidev.app.android.moviecataloguecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sidev.app.android.moviecataloguecompose.ui.nav.Navigation
import sidev.app.android.moviecataloguecompose.ui.page.list.MovieList
import sidev.app.android.moviecataloguecompose.ui.theme.MovieCatalogueComposeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Navigation()
/*
      MovieCatalogueComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
//          Greeting("Android")
          MovieList()
        }
      }
 */
    }
  }
}

/*
@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MovieCatalogueComposeTheme {
    Greeting("Android")
  }
}
 */

@Preview(showBackground = true)
@Composable
fun ListPreview() {
  MovieCatalogueComposeTheme {
    MovieList()
  }
}
 */