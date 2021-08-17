package sidev.app.android.moviecataloguecompose.ui.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sidev.app.android.moviecataloguecompose.di.ViewModelDi
import sidev.app.android.moviecataloguecompose.ui.page.list.ListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory: ViewModelProvider.Factory {
  /**
   * Creates a new instance of the given `Class`.
   *
   *
   *
   * @param modelClass a `Class` whose instance is requested
   * @param <T>        The type parameter for the ViewModel.
   * @return a newly created ViewModel
  </T> */
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T = (when {
    modelClass.isAssignableFrom(ListViewModel::class.java) -> ViewModelDi.src.list()
    else -> throw IllegalArgumentException("Unknown ViewModel Class of '$modelClass'")
  }) as T
}