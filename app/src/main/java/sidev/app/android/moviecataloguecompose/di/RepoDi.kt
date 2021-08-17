package sidev.app.android.moviecataloguecompose.di

import sidev.app.android.moviecataloguecompose.core.data.repo.MovieDetailRepoDummy
import sidev.app.android.moviecataloguecompose.core.data.repo.MovieRepoDummy
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieDetailRepo
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieRepo

object RepoDi {
  var src: RepoDiSrc = RepoDiSrcDummy
}

interface RepoDiSrc {
  fun movie(): MovieRepo
  fun movieDetail(): MovieDetailRepo
}

object RepoDiSrcDummy: RepoDiSrc {
  override fun movie(): MovieRepo = MovieRepoDummy
  override fun movieDetail(): MovieDetailRepo = MovieDetailRepoDummy
}