package sidev.app.android.moviecataloguecompose.ui.nav

import androidx.navigation.NavController
import androidx.navigation.compose.navArgument

enum class Routes(val route: String) {
  ListPage("ListPage") {

                       },
  DetailPage("DetailPage"),
  ;

  abstract fun navigate(
    navController: NavController,
    vararg args: Any,
  )
}