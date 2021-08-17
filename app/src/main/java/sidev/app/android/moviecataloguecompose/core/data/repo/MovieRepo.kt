package sidev.app.android.moviecataloguecompose.core.data.repo

import sidev.app.android.moviecataloguecompose.core.data.dummyMovieList
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieRepo

object MovieRepoDummy: MovieRepo {
  override suspend fun getAllMovie(): List<Movie> = dummyMovieList
}