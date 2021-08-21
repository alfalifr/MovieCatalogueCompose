package sidev.app.android.moviecataloguecompose.ui.page.detail

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieRepo
import sidev.app.android.moviecataloguecompose.util.Const
import java.lang.IllegalArgumentException

class DetailViewModel(
  private val repo: MovieRepo,
): ViewModel() {
  val movieId = MutableLiveData<Int>()
  val movieType = MutableLiveData<String>()

  val movieDetail: LiveData<MovieDetail> = MediatorLiveData<MovieDetail>().apply {
    addSource(movieId) {
      if(it != null) {
        val type = movieType.value ?: return@addSource
        viewModelScope.launch {
          value = getDetail(type, it)
        }
      }
    }
    addSource(movieType) {
      if(it != null) {
        val id = movieId.value ?: return@addSource
        viewModelScope.launch {
          value = getDetail(it, id)
        }
      }
    }
  }

  private suspend fun getDetail(
    type: String,
    id: Int,
  ): MovieDetail = when(type) {
    Const.KEY_TV -> repo.getTvDetail(id)
    Const.KEY_MOVIE -> repo.getMovieDetail(id)
    else -> throw IllegalArgumentException(
      "No such `type` of '$type'"
    )
  }
}