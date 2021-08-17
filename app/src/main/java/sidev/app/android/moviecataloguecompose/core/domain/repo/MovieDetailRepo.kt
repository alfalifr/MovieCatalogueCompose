package sidev.app.android.moviecataloguecompose.core.domain.repo

import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail

interface MovieDetailRepo {
  suspend fun getDetail(id: Int): MovieDetail
}