package sidev.app.android.moviecataloguecompose.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import sidev.app.android.moviecataloguecompose.ui.page.detail.DetailPage
import sidev.app.android.moviecataloguecompose.ui.page.list.ListMainPage
import sidev.app.android.moviecataloguecompose.ui.page.list.ListPage
import sidev.app.android.moviecataloguecompose.ui.theme.AppTheme
import sidev.app.android.moviecataloguecompose.util.Const
import java.lang.IllegalStateException

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Routes.ListMainPage.completeRoute,
  ) {
    composable(
      route = Routes.ListPage.completeRoute,
      arguments = listOf(
        navArgument(Const.KEY_TYPE_INDEX) {
          type = NavType.IntType
        }
      )
    ) { navStack ->
      AppTheme { systemPadding ->
        ListPage(
          pageIndex = navStack.arguments!!.getInt(
            Const.KEY_TYPE_INDEX, 0
          ),
          topPadding = systemPadding.calculateTopPadding(),
          bottomPadding = systemPadding.calculateBottomPadding(),
        ) { movie ->
          Routes.DetailPage.go(
            navController,
            movieId = movie.id,
            movieType = movie.type,
          )
        }
      }
    }
    composable(
      route = Routes.ListMainPage.completeRoute,
    ) {
      AppTheme { systemPadding ->
        ListMainPage(
          systemPadding = systemPadding,
        ) { movie ->
          Routes.DetailPage.go(
            navController = navController,
            movieId = movie.id,
            movieType = movie.type,
          )
        }
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
    ) { navStack ->
      AppTheme { systemPadding ->
        DetailPage(
          movieId = navStack.arguments!!.getInt(Const.KEY_ID),
          movieType = navStack.arguments!!.getString(Const.KEY_TYPE)
            ?: throw IllegalStateException(
              "Nav arg `${Const.KEY_TYPE}` is required"
            ),
          navController = navController,
          systemPadding = systemPadding,
        )
      }
    }
  }
}