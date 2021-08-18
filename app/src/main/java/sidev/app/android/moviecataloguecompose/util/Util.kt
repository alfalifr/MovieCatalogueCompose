package sidev.app.android.moviecataloguecompose.util

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import sidev.app.android.moviecataloguecompose.ui.page.ViewModelFactory

@Composable
inline fun <reified T: ViewModel> defaulViewModel(): T =
  viewModel(factory = ViewModelFactory())