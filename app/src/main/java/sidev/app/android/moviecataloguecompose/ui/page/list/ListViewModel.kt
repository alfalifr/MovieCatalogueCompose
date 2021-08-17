package sidev.app.android.moviecataloguecompose.ui.page.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieRepo

class ListViewModel(
  private val repo: MovieRepo,
): ViewModel() {

  private val _movieList = MutableLiveData<List<Movie>>()
  val movieList: LiveData<List<Movie>>
    get() = _movieList

  init {
    viewModelScope.launch(Dispatchers.IO) {
      _movieList.value = repo.getAllMovie()
    }
  }
}