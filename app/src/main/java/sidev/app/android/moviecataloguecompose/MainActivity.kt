package sidev.app.android.moviecataloguecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.statusBarsPadding
import sidev.app.android.moviecataloguecompose.ui.nav.Navigation

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // This enable us to draw behing system overlay
    WindowCompat.setDecorFitsSystemWindows(window, false)

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