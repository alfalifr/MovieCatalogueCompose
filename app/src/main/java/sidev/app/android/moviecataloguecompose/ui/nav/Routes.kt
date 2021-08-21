package sidev.app.android.moviecataloguecompose.ui.nav

import androidx.navigation.NavController
import sidev.app.android.moviecataloguecompose.util.Const

sealed class Routes(val route: String) {
  object ListPage: Routes("ListPage") {
    override val completeRoute: String
      get() = "$route/{${Const.KEY_TYPE}}"

    fun go(
      navController: NavController,
      movieType: String,
    ) {
      navController.navigate("$route/$movieType")
    }
  }

  object DetailPage: Routes("DetailPage") {
    override val completeRoute: String
      get() = "$route/{${Const.KEY_TYPE}}/{${Const.KEY_ID}}"

    fun go(
      navController: NavController,
      movieId: Int,
      movieType: String,
    ) {
      navController.navigate("$route/$movieType/$movieId")
    }
  }

  open val completeRoute: String
    get() = route

  protected open fun go(navController: NavController,) {
    navController.navigate(route)
  }
}