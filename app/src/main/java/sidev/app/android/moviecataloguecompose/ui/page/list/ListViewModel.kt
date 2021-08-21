package sidev.app.android.moviecataloguecompose.ui.page.list

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieRepo
import sidev.app.android.moviecataloguecompose.util.Const
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ListViewModel(
  private val repo: MovieRepo,
): ViewModel() {

  val movieType = MutableLiveData<String>()

  val movieList: LiveData<List<Movie>> = MediatorLiveData<List<Movie>>().apply {
    addSource(movieType) {
      if(it != null) {
        viewModelScope.launch {
          value = getPopularList(it)
        }
      }
    }
  }

  private suspend fun getPopularList(type: String): List<Movie> = when(type) {
    Const.KEY_TV -> repo.getTvPopular()
    Const.KEY_MOVIE -> repo.getMoviePopular()
    else -> throw IllegalArgumentException(
      "No such `type` of '$type'"
    )
  }
}