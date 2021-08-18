package sidev.app.android.moviecataloguecompose.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import sidev.app.android.moviecataloguecompose.ui.page.detail.DetailPage
import sidev.app.android.moviecataloguecompose.ui.page.list.ListPage

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Routes.ListPage.completeRoute,
  ) {
    composable(route = Routes.ListPage.completeRoute,) {
      ListPage {
        Routes.DetailPage.go(navController, it.id)
      }
    }
    composable(
      route = Routes.DetailPage.completeRoute,
      arguments = listOf(
        navArgument(name = "id") {
          type = NavType.IntType
        },
      ),
    ) {
      DetailPage(movieId = it.arguments!!.getInt("id"))
    }
  }
}