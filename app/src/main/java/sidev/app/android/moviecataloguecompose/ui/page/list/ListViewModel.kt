package sidev.app.android.moviecataloguecompose.ui.page.list

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieRepo
import sidev.app.android.moviecataloguecompose.util.Const
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.*

class ListViewModel(
  private val repo: MovieRepo,
): ViewModel() {

  var isActive = true
    private set

  val topPadding = MutableLiveData(0f) // in Dp

  val pageIndex = MutableLiveData(0)
  private val movieType = Transformations.map(
    Transformations.distinctUntilChanged(pageIndex)
  ) {
    if(it == null) {
      return@map null
    }
    (popularMovieList as MediatorLiveData).value = null
    when(it) {
      0 -> Const.KEY_TV
      1 -> Const.KEY_MOVIE
      else -> throw IllegalStateException(
        "No such `pageIndex` of '$it'"
      )
    }
  }

  val popularMovieList: LiveData<List<Movie>> = MediatorLiveData<List<Movie>>().apply {
    addSource(movieType) {
      if(it != null) {
        viewModelScope.launch {
          value = getPopularList(it)
        }
      } else {
        value = null
      }
    }
  }

  private val _trendingMovieList = MutableLiveData<List<Movie>>()
  val trendingMovieList: LiveData<List<Movie>>
    get() = _trendingMovieList

  private val _activeTrendingIndex = MediatorLiveData<Int>().apply {
    addSource(_trendingMovieList) {
      value = if(it?.isNotEmpty() == true) 0
        else null
    }
  }
  val activeTrendingIndex: LiveData<Int>
    get() = _activeTrendingIndex


  private val trendingCarouselHandler by lazy {
    Handler(Looper.getMainLooper())
  }

  private val trendingCarouselRunnable = Runnable {
    val currSize = _trendingMovieList.value?.size
    if(currSize == null || currSize == 0) {
      _activeTrendingIndex.value = null
    } else {
      _activeTrendingIndex.value =
        _activeTrendingIndex.value
          ?.plus(1)
          ?.rem(currSize)
          ?: 0

      if(isActive) {
        startActiveTrendingCycle(forceRun = true)
      }
    }
  }

  private var isTrendingCycling = false

  fun startActiveTrendingCycle(forceRun: Boolean = false) {
    if(!isTrendingCycling || forceRun) {
      trendingCarouselHandler.postDelayed(
        trendingCarouselRunnable,
        2500,
      )
      isTrendingCycling = true
    }
  }

  private suspend fun getPopularList(type: String): List<Movie> = when(type) {
    Const.KEY_TV -> repo.getTvPopular()
    Const.KEY_MOVIE -> repo.getMoviePopular()
    else -> throw IllegalArgumentException(
      "No such `type` of '$type'"
    )
  }

  fun loadTrendingList(forceLoad: Boolean = false) {
    if(!forceLoad && _trendingMovieList.value != null)
      return
    viewModelScope.launch {
      _trendingMovieList.value = repo.getTrendingList()
    }
  }

  override fun onCleared() {
    super.onCleared()
    isActive = false
  }
}