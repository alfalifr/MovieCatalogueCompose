package sidev.app.android.moviecataloguecompose.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import sidev.app.android.moviecataloguecompose.ui.page.detail.DetailPage
import sidev.app.android.moviecataloguecompose.ui.page.list.ListPage
import sidev.app.android.moviecataloguecompose.util.Const
import java.lang.IllegalStateException

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Routes.ListPage.completeRoute,
  ) {
    composable(
      route = Routes.ListPage.completeRoute,
      arguments = listOf(
        navArgument(Const.KEY_TYPE) {
          type = NavType.StringType
          nullable = true
        }
      )
    ) {
      ListPage(
        movieType = it.arguments!!.getString(Const.KEY_TYPE)
          ?: run {
            //TODO: log simple info
            //"Nav arg `${Const.KEY_TYPE}` is required"
            Const.KEY_TV
          },
      ) { movie ->
        Routes.DetailPage.go(
          navController,
          movieId = movie.id,
          movieType = movie.type,
        )
      }
    }
    composable(
      route = Routes.DetailPage.completeRoute,
      arguments = listOf(
        navArgument(name = Const.KEY_ID) {
          type = NavType.IntType
        },
        navArgument(name = Const.KEY_TYPE) {
          type = NavType.StringType
        },
      ),
    ) {
      DetailPage(
        movieId = it.arguments!!.getInt(Const.KEY_ID),
        movieType = it.arguments!!.getString(Const.KEY_TYPE)
          ?: throw IllegalStateException(
            "Nav arg `${Const.KEY_TYPE}` is required"
          ),
        navController = navController,
      )
    }
  }
}