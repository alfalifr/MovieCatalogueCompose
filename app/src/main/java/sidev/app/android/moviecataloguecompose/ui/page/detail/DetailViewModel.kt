package sidev.app.android.moviecataloguecompose.ui.page.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieDetailRepo

class DetailViewModel(
  private val repo: MovieDetailRepo,
): ViewModel() {
  val movieId = MutableLiveData<Int>()
  val movieDetail = Transformations.switchMap(movieId) { id ->
    val liveData = MutableLiveData<MovieDetail>()
    viewModelScope.launch {
      liveData.value = repo.getDetail(id)
    }
    liveData
  }
}