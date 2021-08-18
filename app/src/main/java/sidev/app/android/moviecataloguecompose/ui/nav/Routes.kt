package sidev.app.android.moviecataloguecompose.ui.nav

import androidx.navigation.NavController

sealed class Routes(val route: String) {
  object ListPage: Routes("ListPage") {
    public override fun go(navController: NavController) {
      super.go(navController)
    }
  }

  object DetailPage: Routes("DetailPage") {
    override val completeRoute: String
      get() = "$route/{id}"

    fun go(
      navController: NavController,
      movieId: Int,
    ) {
      navController.navigate("$route/$movieId")
    }
  }

  open val completeRoute: String
    get() = route

  protected open fun go(navController: NavController,) {
    navController.navigate(route)
  }
}