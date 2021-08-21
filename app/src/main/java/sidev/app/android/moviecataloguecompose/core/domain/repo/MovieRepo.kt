package sidev.app.android.moviecataloguecompose.core.domain.repo

import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail

interface MovieRepo {
  suspend fun getTvPopular(): List<Movie>
  suspend fun getMoviePopular(): List<Movie>

  suspend fun getTvDetail(id: Int): MovieDetail
  suspend fun getMovieDetail(id: Int): MovieDetail
}