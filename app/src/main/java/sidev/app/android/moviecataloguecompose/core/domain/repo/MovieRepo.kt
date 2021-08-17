package sidev.app.android.moviecataloguecompose.core.domain.repo

import sidev.app.android.moviecataloguecompose.core.domain.model.Movie

interface MovieRepo {
  suspend fun getAllMovie(): List<Movie>
}