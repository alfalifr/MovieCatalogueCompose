package sidev.app.android.moviecataloguecompose.core.data.repo

import sidev.app.android.moviecataloguecompose.core.data.getDummyMovieDetail
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieDetailRepo

object MovieDetailRepoDummy: MovieDetailRepo {
  override suspend fun getDetail(id: Int): MovieDetail = getDummyMovieDetail(id)
}