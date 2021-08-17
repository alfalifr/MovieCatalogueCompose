package sidev.app.android.moviecataloguecompose.ui.page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(navController = navController) {

  }
}